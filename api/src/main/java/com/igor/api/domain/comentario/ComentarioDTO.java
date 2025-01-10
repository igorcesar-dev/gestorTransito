package com.igor.api.domain.comentario;

import java.time.LocalDateTime;

public record ComentarioDTO(
        Long id,
        String texto,
        LocalDateTime dataHora) {
}

