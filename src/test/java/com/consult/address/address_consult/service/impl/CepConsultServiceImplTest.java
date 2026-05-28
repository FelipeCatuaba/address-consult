package com.consult.address.address_consult.service.impl;

import com.consult.address.address_consult.cache.CepCacheRepository;
import com.consult.address.address_consult.domain.CepConsultLog;
import com.consult.address.address_consult.dto.CepConsultLogPageResponse;
import com.consult.address.address_consult.dto.CepResponse;
import com.consult.address.address_consult.external.CepExternalClient;
import com.consult.address.address_consult.repository.CepConsultLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CepConsultServiceImplTest {

    @Mock
    private CepExternalClient cepExternalClient;

    @Mock
    private CepCacheRepository cepCacheRepository;

    @Mock
    private CepConsultLogRepository cepConsultLogRepository;

    @InjectMocks
    private CepConsultServiceImpl service;

    @Test
    void shouldReturnCachedResponseAndPersistLog() {
        // arrange
        CepResponse cached = new CepResponse("01001000", "Rua A", "Apto", "Centro", "Sao Paulo", "SP");
        when(cepCacheRepository.findByCep("01001000")).thenReturn(Optional.of(cached));

        // act
        CepResponse result = service.consultCep("01001000");

        // assert
        assertEquals(cached, result);
        verify(cepExternalClient, never()).getAddressByCep(any());
        verify(cepConsultLogRepository).save(any(CepConsultLog.class));
    }

    @Test
    void shouldFetchFromExternalAndCacheWhenMissing() {
        // arrange
        CepResponse external = new CepResponse("01001000", "Rua B", "Casa", "Centro", "Sao Paulo", "SP");
        when(cepCacheRepository.findByCep("01001000")).thenReturn(Optional.empty());
        when(cepExternalClient.getAddressByCep("01001000")).thenReturn(external);

        // act
        CepResponse result = service.consultCep("01001000");

        // assert
        assertEquals(external, result);
        verify(cepCacheRepository).save("01001000", external);
        ArgumentCaptor<CepConsultLog> captor = ArgumentCaptor.forClass(CepConsultLog.class);
        verify(cepConsultLogRepository).save(captor.capture());
        assertEquals("01001000", captor.getValue().getCepConsultado());
    }

    @Test
    void shouldListLogsAndExposeNextPageWhenHasNext() {
        // arrange
        CepConsultLog log = new CepConsultLog();
        log.setId(1L);
        log.setCepConsultado("01001000");
        log.setDataHoraConsulta(LocalDateTime.now());
        log.setCepRetornado("01001000");
        log.setLogradouro("Rua A");
        log.setComplemento("Apto");
        log.setBairro("Centro");
        log.setLocalidade("Sao Paulo");
        log.setUf("SP");
        var page = new PageImpl<>(List.of(log), PageRequest.of(0, 1), 2);
        when(cepConsultLogRepository.findAll(any(PageRequest.class))).thenReturn(page);

        // act
        CepConsultLogPageResponse result = service.listConsultLogs(0, 1);

        // assert
        assertEquals(2, result.totalRegistros());
        assertEquals(1, result.registrosDevolvidos());
        assertEquals(1, result.proximaPagina());
        assertEquals("01001000", result.registros().get(0).cepConsultado());
    }

    @Test
    void shouldListLogsWithoutNextPageWhenLastPage() {
        // arrange
        var page = new PageImpl<CepConsultLog>(List.of(), PageRequest.of(0, 10), 0);
        when(cepConsultLogRepository.findAll(any(PageRequest.class))).thenReturn(page);

        // act
        CepConsultLogPageResponse result = service.listConsultLogs(0, 10);

        // assert
        assertNull(result.proximaPagina());
        assertEquals(0, result.registrosDevolvidos());
    }
}
