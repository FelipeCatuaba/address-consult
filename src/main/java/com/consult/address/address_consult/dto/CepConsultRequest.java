package com.consult.address.address_consult.dto;

import jakarta.validation.constraints.Pattern;

public record CepConsultRequest(
        @Pattern(regexp = "^[0-9]{8}$", message = "O CEP deve conter 8 digitos numericos")
        String cep
) {
}

