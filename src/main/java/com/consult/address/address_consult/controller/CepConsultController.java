package com.consult.address.address_consult.controller;

import com.consult.address.address_consult.dto.CepConsultLogResponse;
import com.consult.address.address_consult.dto.CepConsultRequest;
import com.consult.address.address_consult.dto.CepResponse;
import com.consult.address.address_consult.service.CepConsultService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ceps")
public class CepConsultController {

    private final CepConsultService cepConsultService;

    public CepConsultController(CepConsultService cepConsultService) {
        this.cepConsultService = cepConsultService;
    }

    @GetMapping
    public CepResponse consult(@Valid CepConsultRequest request) {
        return cepConsultService.consultCep(request.cep());
    }

    @GetMapping("/logs")
    public Page<CepConsultLogResponse> listLogs(
            @PageableDefault(sort = "dataHoraConsulta", direction = Sort.Direction.DESC) Pageable pageable) {
        return cepConsultService.listConsultLogs(pageable);
    }
}
