package com.igor.api.domain.comentario;

import java.sql.Date;

public record ComentarioRequestDTO(
        String comentario,
        Date dataHora) {
}
