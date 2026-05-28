package com.consult.address.address_consult.service.impl;

import com.consult.address.address_consult.domain.CepConsultLog;
import com.consult.address.address_consult.dto.CepConsultLogPageResponse;
import com.consult.address.address_consult.dto.CepConsultLogResponse;
import com.consult.address.address_consult.dto.CepResponse;
import com.consult.address.address_consult.external.CepExternalClient;
import com.consult.address.address_consult.repository.CepConsultLogRepository;
import com.consult.address.address_consult.service.CepConsultService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CepConsultServiceImpl implements CepConsultService {

    private final CepExternalClient cepExternalClient;
    private final CepConsultLogRepository cepConsultLogRepository;

    public CepConsultServiceImpl(CepExternalClient cepExternalClient, CepConsultLogRepository cepConsultLogRepository) {
        this.cepExternalClient = cepExternalClient;
        this.cepConsultLogRepository = cepConsultLogRepository;
    }

    @Override
    public CepResponse consultCep(String cep) {
        CepResponse response = cepExternalClient.getAddressByCep(cep);
        saveConsultLog(cep, response);
        return response;
    }

    @Override
    public CepConsultLogPageResponse listConsultLogs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataHoraConsulta"));
        Page<CepConsultLogResponse> logsPage = cepConsultLogRepository.findAll(pageRequest)
                .map(this::toResponse);

        Integer proximaPagina = logsPage.hasNext() ? page + 1 : null;

        return new CepConsultLogPageResponse(
                logsPage.getTotalElements(),
                logsPage.getNumberOfElements(),
                page,
                proximaPagina,
                logsPage.getContent()
        );
    }

    private void saveConsultLog(String cep, CepResponse response) {
        CepConsultLog consultLog = new CepConsultLog();
        consultLog.setCepConsultado(cep);
        consultLog.setDataHoraConsulta(LocalDateTime.now());
        consultLog.setCepRetornado(response.cep());
        consultLog.setLogradouro(response.logradouro());
        consultLog.setComplemento(response.complemento());
        consultLog.setBairro(response.bairro());
        consultLog.setLocalidade(response.localidade());
        consultLog.setUf(response.uf());
        cepConsultLogRepository.save(consultLog);
    }

    private CepConsultLogResponse toResponse(CepConsultLog log) {
        return new CepConsultLogResponse(
                log.getId(),
                log.getCepConsultado(),
                log.getDataHoraConsulta(),
                log.getCepRetornado(),
                log.getLogradouro(),
                log.getComplemento(),
                log.getBairro(),
                log.getLocalidade(),
                log.getUf()
        );
    }
}
