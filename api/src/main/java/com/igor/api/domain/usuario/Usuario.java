package com.igor.api.domain.usuario;

import java.util.List;
import java.util.UUID;

import com.igor.api.domain.comentario.Comentario;
import com.igor.api.domain.ocorrencia.Ocorrencia;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String login;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Ocorrencia> ocorrencias;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;
}
