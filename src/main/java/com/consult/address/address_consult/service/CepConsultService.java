package com.consult.address.address_consult.service;

import com.consult.address.address_consult.dto.CepConsultLogPageResponse;
import com.consult.address.address_consult.dto.CepResponse;

public interface CepConsultService {
    CepResponse consultCep(String cep);

    CepConsultLogPageResponse listConsultLogs(int page, int size);
}
