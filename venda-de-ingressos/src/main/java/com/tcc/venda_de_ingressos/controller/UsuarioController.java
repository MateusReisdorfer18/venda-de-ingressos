package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.Usuario;
import com.tcc.venda_de_ingressos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuario = this.service.findAll();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable UUID id) {
        var usuario = this.service.findById(id);
        if(usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario) {
        var usuarioReturn = this.service.create(usuario);
        if(usuarioReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(usuarioReturn, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> update(@RequestBody @Valid Usuario usuario, @PathVariable UUID id) {
        var usuarioReturn = this.service.update(usuario, id);
        if(usuarioReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuarioReturn);
    }

    @PatchMapping("{id}/status/false")
    public ResponseEntity<Boolean> updateStatusFalse(@PathVariable UUID id) {
        var returnUpdateStatus = this.service.updateStatusFalse(id);
        if(!returnUpdateStatus)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }

    @PatchMapping("{id}/status/true")
    public ResponseEntity<Boolean> updateStatusTrue(@PathVariable UUID id) {
        var returnUpdateUsuario = this.service.updateStatusTrue(id);
        if(!returnUpdateUsuario)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
