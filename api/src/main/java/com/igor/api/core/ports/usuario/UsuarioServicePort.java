package com.igor.api.core.ports.usuario;

import com.igor.api.core.domain.Usuario;

public interface UsuarioServicePort {
    Usuario registerUsuario(Usuario usuario);
}
