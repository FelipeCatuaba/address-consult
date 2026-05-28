package com.consult.address.address_consult.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CepDtoRecordsTest {

    @Test
    void shouldExposeCepResponseFields() {
        // arrange
        CepResponse response = new CepResponse("01001000", "Rua A", "Apto", "Centro", "Sao Paulo", "SP");

        // act + assert
        assertEquals("01001000", response.cep());
        assertEquals("Rua A", response.logradouro());
        assertEquals("Apto", response.complemento());
        assertEquals("Centro", response.bairro());
        assertEquals("Sao Paulo", response.localidade());
        assertEquals("SP", response.uf());
    }

    @Test
    void shouldExposeCepConsultLogResponseFields() {
        // arrange
        LocalDateTime now = LocalDateTime.now();
        CepConsultLogResponse response = new CepConsultLogResponse(1L, "01001000", now, "01001000", "Rua A", "Apto", "Centro", "Sao Paulo", "SP");

        // act + assert
        assertEquals(1L, response.id());
        assertEquals("01001000", response.cepConsultado());
        assertEquals(now, response.dataHoraConsulta());
        assertEquals("01001000", response.cepRetornado());
        assertEquals("Rua A", response.logradouro());
        assertEquals("Apto", response.complemento());
        assertEquals("Centro", response.bairro());
        assertEquals("Sao Paulo", response.localidade());
        assertEquals("SP", response.uf());
    }

    @Test
    void shouldExposeCepConsultLogPageResponseFields() {
        // arrange
        CepConsultLogResponse item = new CepConsultLogResponse(1L, "01001000", LocalDateTime.now(), "01001000", "Rua A", "Apto", "Centro", "Sao Paulo", "SP");
        CepConsultLogPageResponse response = new CepConsultLogPageResponse(10L, 1, 0, null, List.of(item));

        // act + assert
        assertEquals(10L, response.totalRegistros());
        assertEquals(1, response.registrosDevolvidos());
        assertEquals(0, response.paginaAtual());
        assertNull(response.proximaPagina());
        assertEquals(1, response.registros().size());
    }
}
