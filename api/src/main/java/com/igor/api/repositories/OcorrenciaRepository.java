package com.igor.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.api.domain.ocorrencia.Ocorrencia;

public interface OcorrenciaRepository extends
        JpaRepository<Ocorrencia, Long> {
}
