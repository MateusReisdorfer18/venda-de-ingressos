package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.Horario;
import com.tcc.venda_de_ingressos.repository.HorarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository repository;

    public List<Horario> findAll() {
        return this.repository.findAll();
    }

    public Horario findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Horario create(Horario params) {
        return this.repository.save(params);
    }

    public Horario update(Horario params, UUID id) {
        var objectFound = this.findById(id);
        if(objectFound == null)
            return null;

        BeanUtils.copyProperties(params, objectFound);
        return this.repository.save(objectFound);
    }

    public boolean delete(UUID id) {
        var objectFound = this.findById(id);
        if(objectFound == null)
            return false;

        this.repository.delete(objectFound);
        return true;
    }
}
