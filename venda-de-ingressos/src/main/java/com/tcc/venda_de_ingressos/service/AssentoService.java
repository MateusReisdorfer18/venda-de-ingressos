package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.Assento;
import com.tcc.venda_de_ingressos.entity.StatusAssento;
import com.tcc.venda_de_ingressos.entity.StatusSala;
import com.tcc.venda_de_ingressos.repository.AssentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssentoService {
    @Autowired
    private AssentoRepository repository;

    public List<Assento> findAll() {
        return this.repository.findAll();
    }

    public Assento findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Assento create(Assento assento) {
        return this.repository.save(assento);
    }

    public Assento update(Assento assento, UUID id) {
        var assentoEncontrado = this.findById(id);
        if(assentoEncontrado == null)
            return null;

        BeanUtils.copyProperties(assento, assentoEncontrado);
        return this.repository.save(assentoEncontrado);
    }

    public boolean consultDisponibilidade(UUID id) {
        var assento = this.repository.consultDisponibilidade(id, StatusAssento.DISPONIVEL);
        return assento.isPresent();
    }

    public boolean reserveAssento(UUID id) {
        var objectFound = this.findById(id);
        if((objectFound == null) || (objectFound.getStatus() == StatusAssento.RESERVADO))
            return false;

        this.repository.reserveAssento(id, StatusAssento.RESERVADO);
        return true;
    }

    public boolean releaseAssento(UUID id) {
        var objectFound = this.findById(id);
        if(objectFound == null)
            return false;

        this.repository.releaseAssento(id);
        return true;
    }

    public Boolean delete(UUID id) {
        var assento = this.findById(id);
        if(assento == null)
            return false;

        this.repository.delete(assento);
        return true;
    }
}
