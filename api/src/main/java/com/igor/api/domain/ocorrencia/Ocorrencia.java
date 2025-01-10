package com.igor.api.domain.ocorrencia;

import java.sql.Date;
import java.util.*;

import com.igor.api.domain.comentario.Comentario;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "ocorrencia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resumo;
    private String descricao;
    private Date dataHora;
    private String endereco;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "tipo_ocorrencia_id", nullable = false)
    private TipoOcorrencia tipoOcorrencia;  
}