package com.igor.api.domain.ocorrencia;

import java.sql.Date;

public record OcorrenciaRequestDTO(
        String resumo,
        String descricao,
        Date dataHora,
        String endereco,
        Double latitude,
        Double longitude,
        Long tipoOcorrenciaId) {
}
