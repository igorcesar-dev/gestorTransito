package com.igor.api.core.domain;

import java.time.LocalDateTime;

public class Comentario {
    
    private Long id;

    private String comentario;
    private LocalDateTime dataHora;

    private Ocorrencia ocorrencia;

    private Usuario usuario;

    public Comentario() {}

    public Comentario(Long id, String comentario, LocalDateTime dataHora, Ocorrencia ocorrencia, Usuario usuario) {
        this.id = id;
        this.comentario = comentario;
        this.dataHora = dataHora;
        this.ocorrencia = ocorrencia;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}

