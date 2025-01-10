package com.igor.api.domain.ocorrencia;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OcorrenciaSpecification {

    public static Specification<Ocorrencia> tipoEqual(String tipo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipoOcorrencia").get("descricao"), tipo);
    }

    public static Specification<Ocorrencia> dataBetween(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dataHora"), dataInicio, dataFim);
    }

    public static Specification<Ocorrencia> localizacaoContains(String localizacao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("endereco"), "%" + localizacao + "%");
    }

    public static Specification<Ocorrencia> palavraChaveInResumoOrDescricao(String palavraChave) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
            criteriaBuilder.like(root.get("resumo"), "%" + palavraChave + "%"),
            criteriaBuilder.like(root.get("descricao"), "%" + palavraChave + "%")
        );
    }
}
