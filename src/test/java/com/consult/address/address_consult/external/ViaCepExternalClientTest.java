package com.consult.address.address_consult.external;

import com.consult.address.address_consult.dto.CepResponse;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViaCepExternalClientTest {

    @Test
    void shouldCallExternalApiAndMapResponse() throws Exception {
        // arrange
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/ws/01001000/json", exchange -> {
            byte[] body = "{\"cep\":\"01001-000\",\"logradouro\":\"Praca da Se\",\"complemento\":\"lado impar\",\"bairro\":\"Se\",\"localidade\":\"Sao Paulo\",\"uf\":\"SP\"}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(body);
            }
        });
        server.start();

        try {
            ViaCepExternalClient client = new ViaCepExternalClient("http://localhost:" + server.getAddress().getPort());

            // act
            CepResponse response = client.getAddressByCep("01001000");

            // assert
            assertEquals("01001-000", response.cep());
            assertEquals("Praca da Se", response.logradouro());
            assertEquals("SP", response.uf());
        } finally {
            server.stop(0);
        }
    }
}
