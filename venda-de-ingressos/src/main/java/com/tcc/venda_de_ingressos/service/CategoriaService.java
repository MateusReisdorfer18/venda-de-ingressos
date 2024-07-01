package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.Categoria;
import com.tcc.venda_de_ingressos.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return this.repository.findAll();
    }

    public Categoria findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Categoria create(Categoria categoria) {
        return this.repository.save(categoria);
    }

    public Categoria update(Categoria categoria, UUID id) {
        var categoriaEncontrado = this.findById(id);
        if(categoriaEncontrado == null)
            return null;

        BeanUtils.copyProperties(categoria, categoriaEncontrado);
        return this.repository.save(categoriaEncontrado);
    }

    public Boolean delete(UUID id) {
        var categoria = this.findById(id);
        if(categoria == null)
            return false;

        this.repository.delete(categoria);
        return true;
    }
}
