package com.igor.api.domain.comentario;

import java.sql.Date;
import java.util.UUID;

import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.usuario.Usuario;

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
    @GeneratedValue
    private UUID id;

    private String comentario;
    private Date dataHora;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ocorrencia_id", nullable = false)
    private Ocorrencia ocorrencia;
}
