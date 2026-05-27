package com.consult.address.address_consult.dto;

import java.time.LocalDateTime;

public record CepConsultLogResponse(
        Long id,
        String cepConsultado,
        LocalDateTime dataHoraConsulta,
        String cepRetornado,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
