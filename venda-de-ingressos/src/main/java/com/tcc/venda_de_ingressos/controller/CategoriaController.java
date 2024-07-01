package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.Categoria;
import com.tcc.venda_de_ingressos.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categorias = this.service.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable UUID id) {
        var categoria = this.service.findById(id);
        if(categoria == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody @Valid Categoria categoria) {
        var categoriaReturn = this.service.create(categoria);
        if(categoriaReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(categoriaReturn, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> update(@RequestBody @Valid Categoria categoria, @PathVariable UUID id) {
        var categoriaReturn = this.service.update(categoria, id);
        if(categoriaReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoriaReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
