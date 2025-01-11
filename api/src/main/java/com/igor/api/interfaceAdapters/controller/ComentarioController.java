package com.igor.api.interfaceAdapters.controller;

import com.igor.api.application.dto.comentario.ComentarioRequestDTO;
import com.igor.api.application.service.ComentarioService;
import com.igor.api.domain.comentario.Comentario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentario")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping("/ocorrencia/{ocorrenciaId}")
    public ResponseEntity<Comentario> addComentarioToOcorrencia(@PathVariable Long ocorrenciaId,
            @RequestBody ComentarioRequestDTO data,
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Comentario comentario = comentarioService.addComentarioToOcorrencia(ocorrenciaId, data, token);
        return ResponseEntity.ok(comentario);
    }
}