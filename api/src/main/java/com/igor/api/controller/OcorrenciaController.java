package com.igor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.ocorrencia.OcorrenciaRequestDTO;
import com.igor.api.service.OcorrenciaService;

@RestController
@RequestMapping("/api/ocorrencia")
public class OcorrenciaController {
    
    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<Ocorrencia> create(@RequestBody OcorrenciaRequestDTO body){
        Ocorrencia newOcorrencia = this.ocorrenciaService.createOcorrencia(body);
        return ResponseEntity.ok(newOcorrencia);
    }

   /*  @GetMapping
    public ResponseEntity<List<OcorrenciaResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page, size);
        return ResponseEntity.ok(allEvents);
    } */
}
