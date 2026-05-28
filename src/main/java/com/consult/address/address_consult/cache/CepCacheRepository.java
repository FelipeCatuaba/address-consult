package com.consult.address.address_consult.cache;

import com.consult.address.address_consult.dto.CepResponse;

import java.util.Optional;

public interface CepCacheRepository {
    Optional<CepResponse> findByCep(String cep);

    void save(String cep, CepResponse response);
}
