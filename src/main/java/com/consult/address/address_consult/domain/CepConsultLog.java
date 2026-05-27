package com.consult.address.address_consult.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cep_consult_log")
@Getter
@Setter
public class CepConsultLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private String cepConsultado;

    @Column(nullable = false)
    private LocalDateTime dataHoraConsulta;

    @Column(nullable = false, length = 9)
    private String cepRetornado;

    @Column(length = 255)
    private String logradouro;

    @Column(length = 255)
    private String complemento;

    @Column(length = 255)
    private String bairro;

    @Column(length = 255)
    private String localidade;

    @Column(length = 2)
    private String uf;
}

