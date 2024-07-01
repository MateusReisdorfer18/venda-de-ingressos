package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.Ticket;
import com.tcc.venda_de_ingressos.repository.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketRepository repository;

    @Autowired
    private AssentoService assentoService;

    @Autowired
    private HorarioSalaService horarioSalaService;

    public List<Ticket> findAll() {
        return this.repository.findAll();
    }

    public Ticket findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Ticket create(Ticket ticket) {
        var disponibilidadeSala = this.horarioSalaService.consultDisponibilidade(ticket.getSala().getId(), ticket.getHorario().getId());
        var assentoDisponibilidade = this.assentoService.consultDisponibilidade(ticket.getAssento().getId());
        if(!assentoDisponibilidade || !disponibilidadeSala)
            return null;

        var returnReserve = this.assentoService.reserveAssento(ticket.getAssento().getId());
        if(!returnReserve)
            return null;

        return this.repository.save(ticket);
    }

    public Ticket update(Ticket ticket, UUID id) {
        var ticketEncontrado = this.findById(id);
        if(ticketEncontrado == null)
            return null;

        BeanUtils.copyProperties(ticket, ticketEncontrado);
        return this.repository.save(ticketEncontrado);
    }

    public Boolean delete(UUID id) {
        var ticket = this.findById(id);
        if(ticket == null)
            return false;

        this.repository.delete(ticket);
        return true;
    }
}
