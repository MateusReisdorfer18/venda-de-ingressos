package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.Categoria;
import com.tcc.venda_de_ingressos.entity.Classificacao;
import com.tcc.venda_de_ingressos.entity.Filme;
import com.tcc.venda_de_ingressos.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeService service;

    @GetMapping
    public ResponseEntity<List<Filme>> findAll() {
        List<Filme> filmes = this.service.findAll();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Filme>> findByCategoria(@RequestBody @Valid Categoria categoria) {
        List<Filme> filmes = this.service.findByCategoria(categoria);
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/classificacao")
    public ResponseEntity<List<Filme>> findByClassificacao(@RequestBody Map<String, String> params) {
        List<Filme> filmes = this.service.findByClassificacao(params.get("classificacao"));
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/duracao/menor")
    public ResponseEntity<List<Filme>> findByDuracaoLessThanEqual(@RequestBody Map<String, Integer> params) {
        List<Filme> filmes = this.service.findByDuracaoLessThanEqual(params.get("duracao"));
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/duracao/maior")
    public ResponseEntity<List<Filme>> findByDuracaoGreaterThanEqual(@RequestBody Map<String, Integer> params) {
        List<Filme> filmes = this.service.findByDuracaoGreaterThanEqual(params.get("duracao"));
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/disponibilidade/true")
    public ResponseEntity<List<Filme>> findByDisponibilidadeTrue() {
        List<Filme> filmes = this.service.findByDisponibilidadeTrue();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/disponibilidade/false")
    public ResponseEntity<List<Filme>> findByDisponibilidadeFalse() {
        List<Filme> filmes = this.service.findByDisponibilidadeFalse();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> findById(@PathVariable UUID id) {
        var filme = this.service.findById(id);
        if(filme == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(filme);
    }

    @GetMapping("/nome")
    public ResponseEntity<Filme> findByNome(@RequestBody Map<String, String> params) {
        Filme filme = this.service.findByNome(params.get("nome"));
        if(filme == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(filme);
    }

    @PostMapping
    public ResponseEntity<Filme> create(@RequestBody @Valid Filme filme) {
        var filmeReturn = this.service.create(filme);
        if(filmeReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(filmeReturn, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Filme> update(@RequestBody @Valid Filme filme, @PathVariable UUID id) {
        var filmeReturn = this.service.update(filme, id);
        if(filmeReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(filmeReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
