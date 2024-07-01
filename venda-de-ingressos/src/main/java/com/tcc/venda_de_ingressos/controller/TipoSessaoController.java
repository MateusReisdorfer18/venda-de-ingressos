package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.TipoSessao;
import com.tcc.venda_de_ingressos.service.TipoSessaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipo/sessao")
public class TipoSessaoController {
    @Autowired
    private TipoSessaoService service;

    @GetMapping
    public ResponseEntity<List<TipoSessao>> findAll() {
        List<TipoSessao> tipo = this.service.findAll();
        return ResponseEntity.ok(tipo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSessao> findById(@PathVariable UUID id) {
        var tipo = this.service.findById(id);
        if(tipo == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tipo);
    }

    @PostMapping
    public ResponseEntity<TipoSessao> create(@RequestBody @Valid TipoSessao tipoSessao) {
        var tipoSessaoReturn = this.service.create(tipoSessao);
        if(tipoSessaoReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(tipoSessaoReturn, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TipoSessao> update(@RequestBody @Valid TipoSessao tipoSessao, @PathVariable UUID id) {
        var tipoSessaoReturn = this.service.update(tipoSessao, id);
        if(tipoSessaoReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tipoSessaoReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
