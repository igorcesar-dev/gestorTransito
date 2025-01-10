package com.igor.api.domain.tipoOcorrencia;

import java.util.List;
import java.util.UUID;

import com.igor.api.domain.ocorrencia.Ocorrencia;

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
    @GeneratedValue
    private UUID id;

    private String descricao;

    @OneToMany(mappedBy = "tipoOcorrencia")
    private List<Ocorrencia> ocorrencias;
}