package com.consult.address.address_consult.external;

import com.consult.address.address_consult.dto.CepResponse;

record ViaCepResponse(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {

    CepResponse toCepResponse() {
        return new CepResponse(
                cep,
                logradouro,
                complemento,
                bairro,
                localidade,
                uf
        );
    }
}
