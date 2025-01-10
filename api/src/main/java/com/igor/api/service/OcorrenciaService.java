package com.igor.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.ocorrencia.OcorrenciaRequestDTO;
import com.igor.api.domain.ocorrencia.OcorrenciaSpecification;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;
// import com.igor.api.domain.usuario.Usuario;
import com.igor.api.repositories.OcorrenciaRepository;
import com.igor.api.repositories.TipoOcorrenciaRepository;
// import com.igor.api.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final TipoOcorrenciaRepository tipoOcorrenciaRepository;
    // private final UsuarioRepository usuarioRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository,
            TipoOcorrenciaRepository tipoOcorrenciaRepository
    /* UsuarioRepository usuarioRepository */) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.tipoOcorrenciaRepository = tipoOcorrenciaRepository;
        // this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Ocorrencia createOcorrencia(OcorrenciaRequestDTO data) {
        // Busca o TipoOcorrencia pelo ID
        TipoOcorrencia tipoOcorrencia = tipoOcorrenciaRepository.findById(data.tipoOcorrenciaId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "TipoOcorrencia não encontrado com ID: " + data.tipoOcorrenciaId()));

        /*
         * // Busca o Usuario pelo ID
         * Usuario usuario = usuarioRepository.findById(data.usuarioId())
         * .orElseThrow(() -> new
         * EntityNotFoundException("Usuário não encontrado com ID: " +
         * data.usuarioId()));
         */

        // Criação da entidade Ocorrencia
        Ocorrencia newOcorrencia = new Ocorrencia();
        newOcorrencia.setResumo(data.resumo());
        newOcorrencia.setDescricao(data.descricao());
        newOcorrencia.setDataHora(data.dataHora());
        newOcorrencia.setEndereco(data.endereco());
        newOcorrencia.setLatitude(data.latitude());
        newOcorrencia.setLongitude(data.longitude());
        newOcorrencia.setTipoOcorrencia(tipoOcorrencia);
        /* newOcorrencia.setUsuario(usuario); */

        // Salvar a ocorrência no banco de dados
        return ocorrenciaRepository.save(newOcorrencia);
    }

    // Busca todas ocorrências
    @Transactional(readOnly = true)
    public List<Ocorrencia> findAllOcorrencias() {
        return ocorrenciaRepository.findAll();
    }

    // Atualiza uma ocorrência pelo id
    public Ocorrencia updateOcorrencia(Long id, OcorrenciaRequestDTO data) {
        // Busca ocorrência pelo iD
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ocorrência não encontrada com ID: " + id));

        // Busca o tipo da ocorrência
        if (data.tipoOcorrenciaId() != null) {
            TipoOcorrencia tipoOcorrencia = tipoOcorrenciaRepository.findById(data.tipoOcorrenciaId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "TipoOcorrencia não encontrado com ID: " + data.tipoOcorrenciaId()));
            ocorrencia.setTipoOcorrencia(tipoOcorrencia);
        }

        // Atualiza os campo
        if (data.resumo() != null)
            ocorrencia.setResumo(data.resumo());
        if (data.descricao() != null)
            ocorrencia.setDescricao(data.descricao());
        if (data.dataHora() != null)
            ocorrencia.setDataHora(data.dataHora());
        if (data.endereco() != null)
            ocorrencia.setEndereco(data.endereco());
        if (data.latitude() != null)
            ocorrencia.setLatitude(data.latitude());
        if (data.longitude() != null)
            ocorrencia.setLongitude(data.longitude());

        return ocorrenciaRepository.save(ocorrencia);
    }

    // Deleta uma ocorrência pelo id
    @Transactional
    public void deleteOcorrenciaById(Long id) {
        if (!ocorrenciaRepository.existsById(id)) {
            throw new EntityNotFoundException("Ocorrência não encontrada com ID: " + id);
        }
        ocorrenciaRepository.deleteById(id);
    }

    // Pesquisa
    @Transactional(readOnly = true)
    public List<Ocorrencia> searchOcorrencias(String tipo, LocalDateTime dataInicio, LocalDateTime dataFim,
            String localizacao, String palavraChave) {
        Specification<Ocorrencia> specification = Specification.where(null);

        if (tipo != null && !tipo.isEmpty()) {
            specification = specification.and(OcorrenciaSpecification.tipoEqual(tipo));
        }

        if (dataInicio != null && dataFim != null) {
            specification = specification.and(OcorrenciaSpecification.dataBetween(dataInicio, dataFim));
        }

        if (localizacao != null && !localizacao.isEmpty()) {
            specification = specification.and(OcorrenciaSpecification.localizacaoContains(localizacao));
        }

        if (palavraChave != null && !palavraChave.isEmpty()) {
            specification = specification.and(OcorrenciaSpecification.palavraChaveInResumoOrDescricao(palavraChave));
        }

        return ocorrenciaRepository.findAll(specification);
    }

}
