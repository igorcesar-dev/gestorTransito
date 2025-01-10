package com.igor.api.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.igor.api.domain.ocorrencia.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>, JpaSpecificationExecutor<Ocorrencia> {
    @Query("SELECT o.tipoOcorrencia.descricao, COUNT(o) FROM Ocorrencia o " +
            "WHERE o.dataHora >= :dataInicio " +
            "GROUP BY o.tipoOcorrencia.descricao")
    List<Object[]> countOcorrenciasByTipo(@Param("dataInicio") LocalDateTime dataInicio);

    @Query("SELECT o FROM Ocorrencia o LEFT JOIN FETCH o.comentarios WHERE o.id = :id")
    Optional<Ocorrencia> findByIdWithComentarios(@Param("id") Long id);

    @Query("SELECT o FROM Ocorrencia o LEFT JOIN FETCH o.comentarios")
    List<Ocorrencia> findAllWithComentarios();
}
