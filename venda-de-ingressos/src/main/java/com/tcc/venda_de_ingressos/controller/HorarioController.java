package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.Horario;
import com.tcc.venda_de_ingressos.service.HorarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private HorarioService service;

    @GetMapping
    public ResponseEntity<List<Horario>> findAll() {
        List<Horario> horario = this.service.findAll();
        return ResponseEntity.ok(horario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> findById(@PathVariable UUID id) {
        var objectReturn = this.service.findById(id);
        if(objectReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(objectReturn);
    }

    @PostMapping
    public ResponseEntity<Horario> create(@RequestBody @Valid Horario params) {
        var objectReturn = this.service.create(params);
        if(objectReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(objectReturn, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Horario> update(@RequestBody @Valid Horario params, @PathVariable UUID id) {
        var objectReturn = this.service.update(params, id);
        if(objectReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(objectReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
