package com.igor.api.interfaceAdapters.controller;

import com.igor.api.application.service.TipoOcorrenciaService;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrencia;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipo-ocorrencia")
@RequiredArgsConstructor
public class TipoOcorrenciaController {

    private final TipoOcorrenciaService tipoOcorrenciaService;

    
    @GetMapping
    public ResponseEntity<List<TipoOcorrencia>> getAllTipoOcorrencias() {
        List<TipoOcorrencia> tipoOcorrencias = tipoOcorrenciaService.findAll();
        return ResponseEntity.ok(tipoOcorrencias);
    }
}