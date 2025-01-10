package com.igor.api.domain.comentario;

import java.sql.Date;

import com.igor.api.domain.ocorrencia.Ocorrencia;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private Date dataHora;

    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;
}
