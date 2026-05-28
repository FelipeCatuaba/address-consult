package com.consult.address.address_consult.controller;

import com.consult.address.address_consult.dto.CepConsultLogPageResponse;
import com.consult.address.address_consult.dto.CepResponse;
import com.consult.address.address_consult.service.CepConsultService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/ceps")
public class CepConsultController {

    private final CepConsultService cepConsultService;

    public CepConsultController(CepConsultService cepConsultService) {
        this.cepConsultService = cepConsultService;
    }

    @GetMapping
    public CepResponse consult(
            @RequestParam("cep")
            @Pattern(regexp = "^[0-9]{8}$", message = "O CEP deve conter 8 digitos numericos")
            String cep) {
        return cepConsultService.consultCep(cep);
    }

    @GetMapping("/logs")
    public CepConsultLogPageResponse listLogs(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {
        return cepConsultService.listConsultLogs(page, size);
    }
}
