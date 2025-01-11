package com.igor.api.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.igor.api.domain.ocorrencia.Ocorrencia;

import java.time.LocalDateTime;

public class OcorrenciaSpecification {

    public static Specification<Ocorrencia> tipoEqual(Long idTipo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipoOcorrencia").get("id"), idTipo);
    }

    public static Specification<Ocorrencia> dataBetween(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("dataHora"), dataInicio, dataFim);
    }

    public static Specification<Ocorrencia> localizacaoContains(String localizacao) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("endereco"), "%" + localizacao + "%");
    }

    public static Specification<Ocorrencia> palavraChaveInResumoOrDescricao(String palavraChave) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("resumo")), "%" + palavraChave.toLowerCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")),
                        "%" + palavraChave.toLowerCase() + "%"));
    }

}
