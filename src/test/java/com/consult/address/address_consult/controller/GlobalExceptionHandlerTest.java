package com.consult.address.address_consult.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.client.HttpServerErrorException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @Test
    void shouldHandleValidationExceptionWithFieldMessage() throws Exception {
        // arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new DummyRequest(), "obj");
        bindingResult.rejectValue("cep", "invalid", "CEP invalido");
        Method method = GlobalExceptionHandlerTest.class.getDeclaredMethod("dummyMethod", String.class);
        MethodParameter methodParameter = new MethodParameter(method, 0);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);

        // act
        Map<String, String> response = handler.handleValidation(ex);

        // assert
        assertEquals("CEP invalido", response.get("error"));
    }

    @Test
    void shouldHandleConstraintViolationException() {
        // arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("violacao");
        ConstraintViolationException ex = new ConstraintViolationException(Set.of(violation));

        // act
        Map<String, String> response = handler.handleConstraintViolation(ex);

        // assert
        assertEquals("violacao", response.get("error"));
    }

    @Test
    void shouldHandleMissingParameterException() {
        // arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("cep", "String");

        // act
        Map<String, String> response = handler.handleMissingParameter(ex);

        // assert
        assertEquals("Parametro obrigatorio ausente: cep", response.get("error"));
    }

    @Test
    void shouldHandleExternalServerError() {
        // arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        // act
        Map<String, String> response = handler.handleExternalServerError();

        // assert
        assertEquals("Servico externo de CEP indisponivel no momento", response.get("error"));
    }

    @Test
    void shouldHandleExternalNotFound() {
        // arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        // act
        Map<String, String> response = handler.handleExternalNotFound();

        // assert
        assertEquals("CEP nao encontrado", response.get("error"));
    }

    private void dummyMethod(String value) {
    }

    private static class DummyRequest {
        private String cep;

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }
    }
}
