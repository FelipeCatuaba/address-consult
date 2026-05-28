package com.consult.address.address_consult.controller;

import com.consult.address.address_consult.dto.CepConsultLogPageResponse;
import com.consult.address.address_consult.dto.CepResponse;
import com.consult.address.address_consult.service.CepConsultService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CepConsultControllerTest {

    @Test
    void shouldDelegateConsultToService() {
        // arrange
        CepConsultService service = mock(CepConsultService.class);
        CepConsultController controller = new CepConsultController(service);
        CepResponse expected = new CepResponse("01001000", "Rua A", "Apto", "Centro", "Sao Paulo", "SP");
        when(service.consultCep("01001000")).thenReturn(expected);

        // act
        CepResponse result = controller.consult("01001000");

        // assert
        assertEquals(expected, result);
        verify(service).consultCep("01001000");
    }

    @Test
    void shouldDelegateListLogsToService() {
        // arrange
        CepConsultService service = mock(CepConsultService.class);
        CepConsultController controller = new CepConsultController(service);
        CepConsultLogPageResponse expected = new CepConsultLogPageResponse(0, 0, 0, null, List.of());
        when(service.listConsultLogs(0, 10)).thenReturn(expected);

        // act
        CepConsultLogPageResponse result = controller.listLogs(0, 10);

        // assert
        assertEquals(expected, result);
        verify(service).listConsultLogs(0, 10);
    }
}
