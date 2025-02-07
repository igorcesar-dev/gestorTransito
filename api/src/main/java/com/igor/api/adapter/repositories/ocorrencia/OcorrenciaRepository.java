package com.igor.api.adapter.repositories.ocorrencia;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.api.adapter.entities.OcorrenciaEntity;

public interface OcorrenciaRepository extends JpaRepository<OcorrenciaEntity, Long> {
    
}
