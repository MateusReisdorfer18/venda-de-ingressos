package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.TipoSessao;
import com.tcc.venda_de_ingressos.repository.TipoSessaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TipoSessaoService {
    @Autowired
    private TipoSessaoRepository repository;

    public List<TipoSessao> findAll() {
        return this.repository.findAll();
    }

    public TipoSessao findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public TipoSessao create(TipoSessao tipoSessao) {
        return this.repository.save(tipoSessao);
    }

    public TipoSessao update(TipoSessao tipoSessao, UUID id) {
        var tipoSessaoEncontrado = this.findById(id);
        if(tipoSessaoEncontrado == null)
            return null;

        BeanUtils.copyProperties(tipoSessao, tipoSessaoEncontrado);
        return this.repository.save(tipoSessaoEncontrado);
    }

    public Boolean delete(UUID id) {
        var tipoSessaoEncontrado = this.findById(id);
        if(tipoSessaoEncontrado == null)
            return false;

        this.repository.delete(tipoSessaoEncontrado);
        return true;
    }
}
