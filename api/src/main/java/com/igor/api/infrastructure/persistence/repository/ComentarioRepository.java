package com.igor.api.infrastructure.persistence.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.api.domain.comentario.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
        List<Comentario> findByOcorrencia_IdAndDataHoraAfter(Long ocorrenciaId, Date dataHora);
}
