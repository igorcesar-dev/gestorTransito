package com.igor.api.adapter.converters;

import org.springframework.stereotype.Component;

import com.igor.api.adapter.dtos.OcorrenciaDto;
import com.igor.api.adapter.dtos.UsuarioDto;
import com.igor.api.core.domain.Ocorrencia;
import com.igor.api.core.domain.Usuario;

@Component
public class OcorrenciaConverter {

    
    public Usuario toDomain(UsuarioDto usuarioDto) {
        return new Usuario(usuarioDto.getId(), usuarioDto.getNome(), usuarioDto.getLogin(), usuarioDto.getSenha());
    }
    
    public Ocorrencia toDomain(OcorrenciaDto ocorrenciaDto) {
        return new Ocorrencia(ocorrenciaDto.getId(), ocorrenciaDto.getResumo(), ocorrenciaDto.getDescricao(), ocorrenciaDto.getDataHora(), ocorrenciaDto.getEndereco(), ocorrenciaDto.getLatitude(), ocorrenciaDto.getLongitude(), ocorrenciaDto.getTipoOcorrencia(), ocorrenciaDto.getComentarios(), ocorrenciaDto.getUsuario());
    }

    public OcorrenciaDto toDto(Ocorrencia ocorrencia) {
        return new OcorrenciaDto(ocorrencia.getId(), ocorrencia.getResumo(), ocorrencia.getDescricao(),
                ocorrencia.getDataHora(), ocorrencia.getEndereco(), ocorrencia.getLatitude(), ocorrencia.getLongitude(),
                ocorrencia.getTipoOcorrencia().getId(), ocorrencia.getUsuario());
    }
}
