package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.FilmeHorarioDTO;
import com.tcc.venda_de_ingressos.entity.Horario;
import com.tcc.venda_de_ingressos.entity.HorarioSala;
import com.tcc.venda_de_ingressos.entity.Sala;
import com.tcc.venda_de_ingressos.service.HorarioSalaService;
import com.tcc.venda_de_ingressos.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/sala")
public class SalaController {
    @Autowired
    private SalaService service;

    @Autowired
    private HorarioSalaService horarioSalaService;

    @GetMapping
    public ResponseEntity<List<Sala>> findAll() {
        List<Sala> salas = this.service.findAll();
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<Sala>> findByTipo(@RequestBody Map<String, String> params) {
        var salas = this.service.findByTipo(params.get("tipo"));
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/tamanho")
    public ResponseEntity<List<Sala>> findByTamanho(@RequestBody Map<String, String> params) {
        var salas = this.service.findByTamanho(params.get("tamanho"));
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}/horario")
    public ResponseEntity<List<HorarioSala>> findHorarios(@PathVariable UUID id) {
        var horarios = this.horarioSalaService.findBySala(id);
        if(horarios == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}/horarios/disponibilidade")
    public ResponseEntity<List<HorarioSala>> findHorariosBySalaAndDisponibilidade(@PathVariable UUID id, @RequestBody Map<String, Boolean> params) {
        var horarios = this.horarioSalaService.findBySalaAndDisponibilidade(id, params.get("disponibilidade"));
        if(horarios == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> findById(@PathVariable UUID id) {
        var sala = this.service.findById(id);
        if(sala == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(sala);
    }

    @GetMapping("/nome")
    public ResponseEntity<Sala> findByNome(@RequestBody Map<String, String> params) {
        var sala = this.service.findByNome(params.get("nome"));
        if(sala == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(sala);
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<List<Sala>> findByDisponibilidade(@RequestBody Map<String, String> params) {
        var salas = this.service.findByDisponibilidade(params.get("disponibilidade"));
        return ResponseEntity.ok(salas);
    }

    @PostMapping
    public ResponseEntity<Sala> create(@RequestBody @Valid Sala sala) {
        var salaReturn = this.service.create(sala);
        if(salaReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(salaReturn, HttpStatus.CREATED);
    }

    @PostMapping("/horario")
    public ResponseEntity<HorarioSala> addHorario(@RequestBody @Valid HorarioSala horarioSala) {
        var horarioSalaReturn = this.horarioSalaService.create(horarioSala);
        if(horarioSalaReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(horarioSalaReturn);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sala> update(@RequestBody @Valid Sala sala, @PathVariable UUID id) {
        var salaReturn = this.service.update(sala, id);
        if(salaReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(salaReturn);
    }

    @PatchMapping("/{id}/horario")
    public ResponseEntity<Boolean> updateDisponibilidadeHorario(@PathVariable UUID id, @RequestBody @Valid Horario horario) {
        var returnUpdate = this.horarioSalaService.updateDisponibilidadeHorario(id, horario);
        if(!returnUpdate)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }

    @PatchMapping("/filme/horario")
    public ResponseEntity<HorarioSala> addFilmeToHorario(@RequestBody @Valid FilmeHorarioDTO filmeHorario) {
        var returnUpdate = this.horarioSalaService.connectHorarioWithFilme(filmeHorario);
        if(returnUpdate == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(returnUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/horario-sala/{id}")
    public ResponseEntity<Boolean> deleteHorario(@PathVariable UUID id) {
        var returnDelete = this.horarioSalaService.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
