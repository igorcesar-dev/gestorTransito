package com.igor.api.core.ports.ocorrencia;

import com.igor.api.core.domain.Ocorrencia;

public interface OcorrenciaRepositoryPort {
    public Ocorrencia registerOcorrencia(Ocorrencia ocorrencia);
}
