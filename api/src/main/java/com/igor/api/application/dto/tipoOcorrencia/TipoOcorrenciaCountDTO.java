package com.igor.api.application.dto.tipoOcorrencia;

public class TipoOcorrenciaCountDTO {
    private String tipo;
    private Long quantidade;

    public TipoOcorrenciaCountDTO(String tipo, Long quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}

