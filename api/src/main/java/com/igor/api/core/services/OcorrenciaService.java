package com.igor.api.core.services;

import com.igor.api.core.domain.Ocorrencia;
import com.igor.api.core.ports.ocorrencia.OcorrenciaRepositoryPort;
import com.igor.api.core.ports.ocorrencia.OcorrenciaServicePort;

public class OcorrenciaService implements OcorrenciaServicePort {

    private final OcorrenciaRepositoryPort ocorrenciaRepositoryPort;

    public OcorrenciaService(OcorrenciaRepositoryPort ocorrenciaRepositoryPort) {
        this.ocorrenciaRepositoryPort = ocorrenciaRepositoryPort;
    }

    @Override
    public Ocorrencia regiterOcorrencia(Ocorrencia ocorrencia) {
        return ocorrenciaRepositoryPort.registerOcorrencia(ocorrencia);
    }
}