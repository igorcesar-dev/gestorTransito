package com.igor.api.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;

public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {
}
