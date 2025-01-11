package com.igor.api.domain.comentario;

import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
