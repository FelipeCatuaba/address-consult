package com.consult.address.address_consult.dto;

import java.util.List;

public record CepConsultLogPageResponse(
        long totalRegistros,
        int registrosDevolvidos,
        int paginaAtual,
        Integer proximaPagina,
        List<CepConsultLogResponse> registros
) {
}
