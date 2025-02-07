package com.igor.api.adapter.repositories.ocorrencia;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.igor.api.adapter.entities.OcorrenciaEntity;
import com.igor.api.core.domain.Ocorrencia;
import com.igor.api.core.ports.ocorrencia.OcorrenciaRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OcorrenciaRepositoryAdapter implements OcorrenciaRepositoryPort {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Ocorrencia registerOcorrencia(Ocorrencia ocorrencia) {
        OcorrenciaEntity novaOcorrencia = ocorrenciaRepository.save(modelMapper.map(ocorrencia, OcorrenciaEntity.class));
        return modelMapper.map(novaOcorrencia, Ocorrencia.class);
    }
}
