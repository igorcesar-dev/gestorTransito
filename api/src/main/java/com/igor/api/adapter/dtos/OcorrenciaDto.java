package com.igor.api.adapter.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.igor.api.core.domain.Comentario;
import com.igor.api.core.domain.TipoOcorrencia;
import com.igor.api.core.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OcorrenciaDto {
    private Long id;
    private String resumo;
    private String descricao;
    private LocalDateTime dataHora;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private TipoOcorrencia tipoOcorrencia;
    private List<Comentario> comentarios;
    private Usuario usuario;
}