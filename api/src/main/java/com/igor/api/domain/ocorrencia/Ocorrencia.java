package com.igor.api.domain.ocorrencia;

import java.sql.Date;
import java.util.*;

import com.igor.api.domain.comentario.Comentario;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;
import com.igor.api.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "comentario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia {
    @Id
    @GeneratedValue
    private UUID id;

    private String resumo;
    private String descricao;
    private Date dataHora;
    private String endereco;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "tipo_ocorrencia_id", nullable = false)
    private TipoOcorrencia tipoOcorrencia;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "ocorrencia")
    private List<Comentario> comentarios;
}