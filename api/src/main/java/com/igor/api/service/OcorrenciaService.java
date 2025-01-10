package com.igor.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.igor.api.domain.comentario.ComentarioDTO;
import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.ocorrencia.OcorrenciaComComentariosDTO;
import com.igor.api.domain.ocorrencia.OcorrenciaRequestDTO;
import com.igor.api.domain.ocorrencia.OcorrenciaSpecification;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrenciaCountDTO;
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
        // Valida se a ocorrência ocorreu há mais de 2 dias
        if (data.dataHora() != null && data.dataHora().isBefore(LocalDateTime.now().minusDays(2))) {
            throw new IllegalArgumentException("Ocorrências não podem ser cadastradas se ocorreram há mais de 2 dias.");
        }

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

    private OcorrenciaComComentariosDTO toDto(Ocorrencia ocorrencia) {
        return new OcorrenciaComComentariosDTO(
                ocorrencia.getId(),
                ocorrencia.getResumo(),
                ocorrencia.getDescricao(),
                ocorrencia.getDataHora(),
                ocorrencia.getEndereco(),
                ocorrencia.getLatitude(),
                ocorrencia.getLongitude(),
                ocorrencia.getTipoOcorrencia() != null ? ocorrencia.getTipoOcorrencia().getDescricao() : null,
                ocorrencia.getComentarios().stream().map(comentario -> new ComentarioDTO(
                        comentario.getId(),
                        comentario.getComentario(),
                        comentario.getDataHora())).toList());
    }

    // Busca todas as ocorrências com comentários
    @Transactional(readOnly = true)
    public List<OcorrenciaComComentariosDTO> findAll() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll(); // Isso vai buscar todas as ocorrências
        return ocorrencias.stream()
                .map(this::toDto) // Converter cada ocorrência para DTO com comentários
                .collect(Collectors.toList());
    }

    // Busca uma ocorrência pelo id
    @Transactional(readOnly = true)
    public OcorrenciaComComentariosDTO findOcorrenciaWithComentariosById(Long id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ocorrência não encontrada com ID: " + id));

        return toDto(ocorrencia); // Converte a Ocorrencia em DTO com Comentários
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

    @Transactional(readOnly = true)
    public List<TipoOcorrenciaCountDTO> countOcorrenciasByTipoLast30Days() {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(30);
        List<Object[]> results = ocorrenciaRepository.countOcorrenciasByTipo(dataInicio);

        return results.stream()
                .map(result -> new TipoOcorrenciaCountDTO((String) result[0], (Long) result[1]))
                .toList();
    }

}
