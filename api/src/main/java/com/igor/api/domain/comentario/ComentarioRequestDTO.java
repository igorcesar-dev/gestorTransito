package com.igor.api.domain.comentario;

import java.time.LocalDateTime;

public record ComentarioRequestDTO(
        String comentario,
        LocalDateTime dataHora) {
}
