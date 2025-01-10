package com.igor.api.domain.ocorrencia;

import java.time.LocalDateTime;

public record OcorrenciaRequestDTO(
        String resumo,
        String descricao,
        LocalDateTime dataHora,
        String endereco,
        Double latitude,
        Double longitude,
        Long tipoOcorrenciaId) {
}
