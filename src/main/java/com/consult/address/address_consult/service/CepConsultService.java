package com.consult.address.address_consult.service;

import com.consult.address.address_consult.dto.CepConsultLogResponse;
import com.consult.address.address_consult.dto.CepResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CepConsultService {
    CepResponse consultCep(String cep);

    Page<CepConsultLogResponse> listConsultLogs(Pageable pageable);
}
