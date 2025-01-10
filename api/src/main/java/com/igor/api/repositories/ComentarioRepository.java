package com.igor.api.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.api.domain.comentario.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
        List<Comentario> findByOcorrencia_IdAndDataHoraAfter(Long ocorrenciaId, Date dataHora);
}
