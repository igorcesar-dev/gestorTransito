package com.igor.api.controller;

import com.igor.api.domain.comentario.Comentario;
import com.igor.api.domain.comentario.ComentarioRequestDTO;
import com.igor.api.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentario")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping("/ocorrencia/{ocorrenciaId}")
    public ResponseEntity<Comentario> addCouponsToEvent(@PathVariable Long ocorrenciaId, @RequestBody ComentarioRequestDTO data) {
        Comentario comentarios = comentarioService.addComentarioToOcorrencia(ocorrenciaId, data);
        return ResponseEntity.ok(comentarios);
    }
}