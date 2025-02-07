package com.igor.api.core.ports.usuario;

import com.igor.api.core.domain.Usuario;

public interface UsuarioRepositoryPort {
    public Usuario registerUsuario(Usuario usuario);
}
