package com.igor.api.core.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Ocorrencia {
    private Long id;
    private String resumo;
    private String descricao;
    private LocalDateTime dataHora;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private TipoOcorrencia tipoOcorrencia;
    private List<Comentario> comentarios;
    private Usuario usuario;

    public Ocorrencia(){}

    public Ocorrencia(Long id, String resumo, String descricao, LocalDateTime dataHora, String endereco, Double latitude, Double longitude, TipoOcorrencia tipoOcorrencia, List<Comentario> comentarios, Usuario usuario) {
        this.id = id;
        this.resumo = resumo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tipoOcorrencia = tipoOcorrencia;
        this.comentarios = comentarios;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public TipoOcorrencia getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
