package com.igor.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;

public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {
}
