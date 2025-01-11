package com.igor.api.application.dto.ocorrencia;

import java.time.LocalDateTime;
import java.util.List;

import com.igor.api.application.dto.comentario.ComentarioDTO;

public record OcorrenciaComComentariosDTO(
        Long id,
        String resumo,
        String descricao,
        LocalDateTime dataHora,
        String endereco,
        Double latitude,
        Double longitude,
        String tipoOcorrencia,
        List<ComentarioDTO> comentarios
        ) {
}
