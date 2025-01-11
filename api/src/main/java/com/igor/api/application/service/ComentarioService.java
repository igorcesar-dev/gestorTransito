package com.igor.api.application.service;

import com.igor.api.application.dto.comentario.ComentarioRequestDTO;
import com.igor.api.domain.comentario.Comentario;
import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.usuario.Usuario;
import com.igor.api.infrastructure.persistence.repository.ComentarioRepository;
import com.igor.api.infrastructure.persistence.repository.OcorrenciaRepository;
import com.igor.api.infrastructure.persistence.repository.UsuarioRepository;
import com.igor.api.infrastructure.security.TokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    /*
     * private final ComentarioRepository comentarioRepository;
     * private final OcorrenciaRepository ocorrenciaRepository;
     * 
     * public Comentario addComentarioToOcorrencia(Long ocorrenciaId,
     * ComentarioRequestDTO comentarioData) {
     * Ocorrencia ocorrencia = ocorrenciaRepository.findById(ocorrenciaId)
     * .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encotrada"));
     * 
     * Comentario comentario = new Comentario();
     * comentario.setComentario(comentarioData.comentario());
     * comentario.setOcorrencia(ocorrencia);
     * 
     * return comentarioRepository.save(comentario);
     * }
     */

    private final ComentarioRepository comentarioRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    public Comentario addComentarioToOcorrencia(Long ocorrenciaId, ComentarioRequestDTO comentarioData, String token) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(ocorrenciaId)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada"));

        String loginUsuario = tokenService.validateToken(token);
        if (loginUsuario.isEmpty()) {
            throw new IllegalArgumentException("Token inválido ou usuário não autenticado.");
        }

        // Obtendo o UserDetails e verificando se é do tipo Usuario
        UserDetails userDetails = usuarioRepository.findByLogin(loginUsuario);
        if (!(userDetails instanceof Usuario usuario)) {
            throw new IllegalArgumentException("Usuário não é uma instância válida.");
        }

        // Criando e salvando o comentário
        Comentario comentario = new Comentario();
        comentario.setComentario(comentarioData.comentario());
        comentario.setDataHora(LocalDateTime.now());
        comentario.setOcorrencia(ocorrencia);
        comentario.setUsuario(usuario);

        return comentarioRepository.save(comentario);
    }

    public List<Comentario> consultComentarios(Long ocorrenciaId, Date currentDate) {
        return comentarioRepository.findByOcorrencia_IdAndDataHoraAfter(ocorrenciaId, currentDate);
    }
}
