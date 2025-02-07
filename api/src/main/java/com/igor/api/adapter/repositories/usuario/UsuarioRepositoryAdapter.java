package com.igor.api.adapter.repositories.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.igor.api.adapter.entities.UsuarioEntity;
import com.igor.api.core.domain.Usuario;
import com.igor.api.core.ports.usuario.UsuarioRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public Usuario registerUsuario(Usuario usuario) {
        UsuarioEntity novoUsuario = usuarioRepository.save(modelMapper.map(usuario, UsuarioEntity.class));
        return modelMapper.map(novoUsuario, Usuario.class);
    }
}
