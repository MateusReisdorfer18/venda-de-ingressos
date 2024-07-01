package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.Usuario;
import com.tcc.venda_de_ingressos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return this.repository.findAll();
    }

    public Usuario findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Usuario create(Usuario usuario) {
        return this.repository.save(usuario);
    }

    public Usuario update(Usuario usuario, UUID id) {
        var usuarioEncontrado = this.findById(id);
        if(usuarioEncontrado == null)
            return null;

        BeanUtils.copyProperties(usuario, usuarioEncontrado);
        return this.repository.save(usuarioEncontrado);
    }

    public boolean updateStatusFalse(UUID id) {
        var usuario = this.findById(id);
        if(usuario == null)
            return false;

        this.repository.updateStatusFalse(id);
        return true;
    }

    public boolean updateStatusTrue(UUID id) {
        var usuario = this.findById(id);
        if(usuario == null)
            return false;

        this.repository.updateStatusTrue(id);
        return true;
    }

    public Boolean delete(UUID id) {
        var usuarioEncontrado = this.findById(id);
        if(usuarioEncontrado == null)
            return false;

        this.repository.delete(usuarioEncontrado);
        return true;
    }
}
