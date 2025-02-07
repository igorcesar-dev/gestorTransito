package com.igor.api.adapter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.igor.api.adapter.converters.OcorrenciaConverter;
import com.igor.api.adapter.converters.TipoOcorrenciaConverter;
import com.igor.api.adapter.dtos.TipoOcorrenciaDto;
import com.igor.api.adapter.dtos.UsuarioDto;
import com.igor.api.core.ports.ocorrencia.OcorrenciaServicePort;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/tipo-ocorrencia")
@RequiredArgsConstructor
public class TipoOcorrenciaController {
    
    private final TipoOcorrenciaService tipoOcorrenciaService;
    private final TipoOcorrenciaConverter tipoOcorrenciaConverter;


    @GetMapping
    public TipoOcorrenciaDto register(@RequestBody UsuarioDto registerDto) {
        return usuarioConverter
        .toDto(usuarioServicePort.registerUsuario(usuarioConverter.toDomain(registerDto)));
    }



}
