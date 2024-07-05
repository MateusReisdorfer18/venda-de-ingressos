package com.tcc.venda_de_ingressos.controller;

import com.tcc.venda_de_ingressos.entity.Ticket;
import com.tcc.venda_de_ingressos.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService service;

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll() {
        var tickets = this.service.findAll();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable UUID id) {
        var ticket = this.service.findById(id);
        if(ticket == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody @Valid Ticket ticket) {
        var ticketReturn = this.service.create(ticket);
        if(ticketReturn == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(ticketReturn, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ticket> update(@RequestBody @Valid Ticket ticket, @PathVariable UUID id) {
        var ticketReturn = this.service.update(ticket, id);
        if(ticketReturn == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ticketReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        var returnDelete = this.service.delete(id);
        if(!returnDelete)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
