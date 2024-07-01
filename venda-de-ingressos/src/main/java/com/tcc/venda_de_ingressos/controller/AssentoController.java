package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.Assento;
import com.tcc.venda_de_ingressos.service.AssentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assento")
public class AssentoController {
    @Autowired
    private AssentoService service;

    @GetMapping
    public ResponseEntity<List<Assento>> findAll() {
        List<Assento> assentos = this.service.findAll();
        return ResponseEntity.ok(assentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assento> findById(@PathVariable UUID id) {
        var assentos = this.service.findById(id);
        if(assentos == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(assentos);
    }

    @PostMapping
    public ResponseEntity<Assento> create(@RequestBody @Valid Assento assento) {
        var assentoReturn = this.service.create(assento);
        if(assentoReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(assento, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Assento> update(@RequestBody @Valid Assento assento, @PathVariable UUID id) {
        var assentoReturn = this.service.update(assento, id);
        if(assentoReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(assentoReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
