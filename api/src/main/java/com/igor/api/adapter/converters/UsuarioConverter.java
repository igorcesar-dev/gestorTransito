package com.igor.api.adapter.converters;

import org.springframework.stereotype.Component;

import com.igor.api.adapter.dtos.UsuarioDto;
import com.igor.api.core.domain.Usuario;

@Component
public class UsuarioConverter {
    
    public Usuario toDomain(UsuarioDto usuarioDto) {
        return new Usuario(usuarioDto.getId(), usuarioDto.getNome(), usuarioDto.getLogin(), usuarioDto.getSenha());
    }

    public UsuarioDto toDto (Usuario usuario){
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha());
    }
}
