package com.consult.address.address_consult.external;

import com.consult.address.address_consult.dto.CepResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViaCepResponseTest {

    @Test
    void shouldConvertToCepResponse() {
        // arrange
        ViaCepResponse via = new ViaCepResponse("01001-000", "Praca da Se", "lado impar", "Se", "Sao Paulo", "SP");

        // act
        CepResponse result = via.toCepResponse();

        // assert
        assertEquals("01001-000", result.cep());
        assertEquals("Praca da Se", result.logradouro());
        assertEquals("SP", result.uf());
    }
}
