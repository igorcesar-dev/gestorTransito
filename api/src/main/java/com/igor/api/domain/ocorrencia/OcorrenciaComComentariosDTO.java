package com.igor.api.domain.ocorrencia;

import java.time.LocalDateTime;
import java.util.List;

import com.igor.api.domain.comentario.ComentarioDTO;

public record OcorrenciaComComentariosDTO(
        Long id,
        String resumo,
        String descricao,
        LocalDateTime dataHora,
        String endereco,
        Double latitude,
        Double longitude,
        String tipoOcorrencia,
        List<ComentarioDTO> comentarios) {
}
