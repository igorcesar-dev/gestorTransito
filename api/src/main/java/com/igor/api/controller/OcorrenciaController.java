package com.igor.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igor.api.domain.ocorrencia.Ocorrencia;
import com.igor.api.domain.ocorrencia.OcorrenciaComComentariosDTO;
import com.igor.api.domain.ocorrencia.OcorrenciaRequestDTO;
import com.igor.api.domain.tipoOcorrencia.TipoOcorrenciaCountDTO;
import com.igor.api.service.OcorrenciaService;

@RestController
@RequestMapping("/api/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<Ocorrencia> create(@RequestBody OcorrenciaRequestDTO body) {
        Ocorrencia newOcorrencia = this.ocorrenciaService.createOcorrencia(body);
        return ResponseEntity.ok(newOcorrencia);
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaComComentariosDTO>> getAllOcorrencias() {
        List<OcorrenciaComComentariosDTO> ocorrencias = ocorrenciaService.findAll();
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaComComentariosDTO> getOcorrenciaWithComentarios(@PathVariable Long id) {
        OcorrenciaComComentariosDTO ocorrencia = ocorrenciaService.findOcorrenciaWithComentariosById(id);
        return ResponseEntity.ok(ocorrencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocorrencia> updatOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaRequestDTO data) {
        Ocorrencia updatedOcorrencia = ocorrenciaService.updateOcorrencia(id, data);
        return ResponseEntity.ok(updatedOcorrencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOcorrencia(@PathVariable Long id) {
        ocorrenciaService.deleteOcorrenciaById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Ocorrencia>> searchOcorrencias(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) String palavraChave) {
        List<Ocorrencia> ocorrencias = ocorrenciaService.searchOcorrencias(tipo, dataInicio, dataFim, localizacao,
                palavraChave);
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/quantidade-ultimas-ocorrencias")
    public ResponseEntity<List<TipoOcorrenciaCountDTO>> countOcorrenciasByTipo() {
        List<TipoOcorrenciaCountDTO> counts = ocorrenciaService.countOcorrenciasByTipoLast30Days();
        return ResponseEntity.ok(counts);
    }

}
