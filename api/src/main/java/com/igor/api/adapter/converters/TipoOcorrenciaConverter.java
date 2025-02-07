package com.igor.api.adapter.converters;

import org.springframework.stereotype.Component;

import com.igor.api.adapter.dtos.TipoOcorrenciaDto;
import com.igor.api.core.domain.TipoOcorrencia;

@Component
public class TipoOcorrenciaConverter {
    
    public TipoOcorrencia toDomain(TipoOcorrencia tipoOcorrenciaDto) {
        return new TipoOcorrencia(tipoOcorrenciaDto.getId(), tipoOcorrenciaDto.getDescricao());
    }

    public TipoOcorrenciaDto toDto (TipoOcorrencia tipoOcorrencia){
        return new TipoOcorrenciaDto(tipoOcorrencia.getId(), tipoOcorrencia.getDescricao());
    }
}
