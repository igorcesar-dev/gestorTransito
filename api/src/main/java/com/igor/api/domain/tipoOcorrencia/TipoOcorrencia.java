package com.igor.api.domain.tipoOcorrencia;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tipo_ocorrencia")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoOcorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
}