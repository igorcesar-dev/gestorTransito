package com.igor.api.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.api.application.dto.comentario.ComentarioDTO;
import com.igor.api.application.dto.ocorrencia.OcorrenciaComComentariosDTO;
import com.igor.api.application.dto.ocorrencia.OcorrenciaRequestDTO;
import com.igor.api.application.dto.tipoOcorrencia.TipoOcorrenciaCountDTO;
import com.igor.api.application.dto.usuario.UsuarioDTO;
import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;
import com.igor.api.domain.usuario.Usuario;
import com.igor.api.infrastructure.persistence.repository.OcorrenciaRepository;
import com.igor.api.infrastructure.persistence.repository.TipoOcorrenciaRepository;
import com.igor.api.infrastructure.persistence.repository.UsuarioRepository;
import com.igor.api.infrastructure.persistence.specifications.OcorrenciaSpecification;
import com.igor.api.infrastructure.security.TokenService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final TipoOcorrenciaRepository tipoOcorrenciaRepository;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository,
            TipoOcorrenciaRepository tipoOcorrenciaRepository, TokenService tokenService,
            UsuarioRepository usuarioRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.tipoOcorrenciaRepository = tipoOcorrenciaRepository;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Ocorrencia createOcorrencia(OcorrenciaRequestDTO data, String token) {
        // Valida se a ocorrência ocorreu há mais de 2 dias
        if (data.dataHora() != null && data.dataHora().isBefore(LocalDateTime.now().minusDays(2))) {
            throw new IllegalArgumentException("Ocorrências não podem ser cadastradas se ocorreram há mais de 2 dias.");
        }

        // Busca o TipoOcorrencia pelo ID
        TipoOcorrencia tipoOcorrencia = tipoOcorrenciaRepository.findById(data.tipoOcorrenciaId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de ocorrência não encontrado com ID: " + data.tipoOcorrenciaId()));

        String loginUsuario = tokenService.validateToken(token);
        if (loginUsuario.isEmpty()) {
            throw new IllegalArgumentException("Token inválido ou usuário não autenticado.");
        }

        // Obtendo o UserDetails
        UserDetails userDetails = usuarioRepository.findByLogin(loginUsuario);
        if (!(userDetails instanceof Usuario usuario)) {
            throw new IllegalArgumentException("Usuário não é uma instância válida.");
        }

        // Criação da entidade Ocorrencia
        Ocorrencia newOcorrencia = new Ocorrencia();
        newOcorrencia.setResumo(data.resumo());
        newOcorrencia.setDescricao(data.descricao());
        newOcorrencia.setDataHora(data.dataHora());
        newOcorrencia.setEndereco(data.endereco());
        newOcorrencia.setLatitude(data.latitude());
        newOcorrencia.setLongitude(data.longitude());
        newOcorrencia.setTipoOcorrencia(tipoOcorrencia);
        newOcorrencia.setUsuario(usuario);

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
                ocorrencia.getComentarios().stream().map(comentario -> {
                    var usuario = comentario.getUsuario();
                    var usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getLogin());
                    return new ComentarioDTO(
                            comentario.getId(),
                            comentario.getComentario(),
                            comentario.getDataHora(),
                            usuarioDTO);
                }).toList());
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
    public void deleteOcorrenciaById(Long id, String token) {
        // Busca a ocorrência pelo ID
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ocorrência não encontrada com ID: " + id));

        // Valida o token e obtém o login do usuário autenticado
        String loginUsuario = tokenService.validateToken(token);
        if (loginUsuario == null || loginUsuario.isEmpty()) {
            throw new IllegalArgumentException("Token inválido ou usuário não autenticado.");
        }

        // Verifica se o usuário que criou a ocorrência é o mesmo que está autenticado
        if (!ocorrencia.getUsuario().getLogin().equals(loginUsuario)) {
            throw new IllegalArgumentException("Somente o usuário que criou a ocorrência pode deletá-la.");
        }

        // Deleta a ocorrência
        ocorrenciaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Ocorrencia> searchOcorrencias(Long idTipo, LocalDateTime dataInicio, LocalDateTime dataFim,
            String localizacao, String palavraChave) {
        Specification<Ocorrencia> specification = Specification.where(null);

        if (idTipo != null) {
            specification = specification.and(OcorrenciaSpecification.tipoEqual(idTipo));
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
