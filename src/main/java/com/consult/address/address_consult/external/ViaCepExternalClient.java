package com.consult.address.address_consult.external;

import com.consult.address.address_consult.dto.CepResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ViaCepExternalClient implements CepExternalClient {

    private final RestClient restClient;

    public ViaCepExternalClient(@Value("${external.cep-api.base-url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public CepResponse getAddressByCep(String cep) {
        ViaCepResponse viaCepResponse = restClient
                .get()
                .uri("/ws/{cep}/json", cep)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ViaCepResponse.class);

        return viaCepResponse.toCepResponse();
    }
}
