package com.igor.api.application.dto.comentario;

import java.time.LocalDateTime;

import com.igor.api.application.dto.usuario.UsuarioDTO;

public record ComentarioDTO(
        Long id,
        String texto,
        LocalDateTime dataHora,
        UsuarioDTO usuario) {
}
