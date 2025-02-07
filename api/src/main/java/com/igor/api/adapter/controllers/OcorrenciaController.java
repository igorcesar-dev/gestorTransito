package com.igor.api.adapter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.api.adapter.converters.OcorrenciaConverter;
import com.igor.api.core.ports.ocorrencia.OcorrenciaServicePort;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/ocorrencia")
@RequiredArgsConstructor
public class OcorrenciaController {
    
    private final OcorrenciaServicePort ocorrenciaServicePort;
    private final OcorrenciaConverter ocorrenciaConverter;



}
