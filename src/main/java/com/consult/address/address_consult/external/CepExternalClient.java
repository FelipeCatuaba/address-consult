package com.consult.address.address_consult.external;

import com.consult.address.address_consult.dto.CepResponse;

public interface CepExternalClient {
    CepResponse getAddressByCep(String cep);
}

