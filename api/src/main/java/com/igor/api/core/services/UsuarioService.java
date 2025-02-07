package com.igor.api.core.services;

import com.igor.api.core.domain.Usuario;
import com.igor.api.core.ports.usuario.UsuarioRepositoryPort;
import com.igor.api.core.ports.usuario.UsuarioServicePort;

public class UsuarioService implements UsuarioServicePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }
    
    @Override
    public Usuario registerUsuario(Usuario usuario) {
        return usuarioRepositoryPort.registerUsuario(usuario);
    }
}
