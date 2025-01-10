package com.igor.api.service;

import com.igor.api.domain.comentario.Comentario;
import com.igor.api.domain.comentario.ComentarioRequestDTO;
import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.repositories.ComentarioRepository;
import com.igor.api.repositories.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public Comentario addComentarioToOcorrencia(Long ocorrenciaId, ComentarioRequestDTO comentarioData) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(ocorrenciaId)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encotrada"));

        Comentario comentario = new Comentario();
        comentario.setComentario(comentarioData.comentario());
        comentario.setDataHora(comentarioData.dataHora());
        comentario.setOcorrencia(ocorrencia);

        return comentarioRepository.save(comentario);
    }

    public List<Comentario> consultComentarios(Long ocorrenciaId, Date currentDate) {
        return comentarioRepository.findByOcorrencia_IdAndDataHoraAfter(ocorrenciaId, currentDate);
    }
}
