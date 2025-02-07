package com.igor.api.adapter.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.igor.api.core.domain.Comentario;
import com.igor.api.core.domain.TipoOcorrencia;
import com.igor.api.core.domain.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Table(name = "ocorrencia")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OcorrenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resumo;
    private String descricao;
    private LocalDateTime dataHora;
    private String endereco;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "tipo_ocorrencia_id", nullable = false)
    private TipoOcorrencia tipoOcorrencia;

    @OneToMany(mappedBy = "ocorrencia")
    @JsonIgnore
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
