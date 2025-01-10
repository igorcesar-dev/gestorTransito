package com.igor.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.ocorrencia.OcorrenciaRequestDTO;
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

        /* // Busca o Usuario pelo ID
        Usuario usuario = usuarioRepository.findById(data.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + data.usuarioId())); */

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
}
