package com.igor.api.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;
import com.igor.api.infrastructure.persistence.repository.TipoOcorrenciaRepository;

@Service
public class TipoOcorrenciaService {

    private final TipoOcorrenciaRepository tipoOcorrenciaRepository;

    public TipoOcorrenciaService(TipoOcorrenciaRepository tipoOcorrenciaRepository) {
        this.tipoOcorrenciaRepository = tipoOcorrenciaRepository;
    }

    // Busca todos os tipos de ocorrências
    @Transactional(readOnly = true)
    public List<TipoOcorrencia> findAll() {
        List<TipoOcorrencia> tipoOcorrencias = tipoOcorrenciaRepository.findAll();
        return tipoOcorrencias.stream()
                .collect(Collectors.toList());
    }
}
