package com.consult.address.address_consult.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CepConsultLogTest {

    @Test
    void shouldSetAndGetAllFields() {
        // arrange
        CepConsultLog log = new CepConsultLog();
        LocalDateTime now = LocalDateTime.now();

        // act
        log.setId(1L);
        log.setCepConsultado("01001000");
        log.setDataHoraConsulta(now);
        log.setCepRetornado("01001000");
        log.setLogradouro("Rua A");
        log.setComplemento("Apto");
        log.setBairro("Centro");
        log.setLocalidade("Sao Paulo");
        log.setUf("SP");

        // assert
        assertEquals(1L, log.getId());
        assertEquals("01001000", log.getCepConsultado());
        assertEquals(now, log.getDataHoraConsulta());
        assertEquals("01001000", log.getCepRetornado());
        assertEquals("Rua A", log.getLogradouro());
        assertEquals("Apto", log.getComplemento());
        assertEquals("Centro", log.getBairro());
        assertEquals("Sao Paulo", log.getLocalidade());
        assertEquals("SP", log.getUf());
    }
}
