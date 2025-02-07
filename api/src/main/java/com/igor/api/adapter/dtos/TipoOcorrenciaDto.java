package com.igor.api.adapter.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoOcorrenciaDto {
    private Long id;
    private String descricao;
}
