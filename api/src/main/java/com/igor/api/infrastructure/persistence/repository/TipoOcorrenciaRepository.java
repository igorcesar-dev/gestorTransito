package com.igor.api.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;

@Repository
public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {
}
