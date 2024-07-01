package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.*;
import com.tcc.venda_de_ingressos.repository.SalaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private AssentoService assentoService;

    public List<Sala> findAll() {
        return this.repository.findAll();
    }

    public List<Sala> findByTipo(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "TEATRO" -> this.repository.findByTipo(TipoSala.TEATRO);
            case "CINEMA" -> this.repository.findByTipo(TipoSala.CINEMA);
            case "SHOW" -> this.repository.findByTipo(TipoSala.SHOW);
            case "EVENTO" -> this.repository.findByTipo(TipoSala.EVENTO);
            default -> new ArrayList<>();
        };
    }

    public List<Sala> findByTamanho(String tamanho) {
        return switch (tamanho.toUpperCase()) {
            case "GRANDE" -> this.repository.findByTamanho(TamanhoSala.GRANDE);
            case "MEDIA" -> this.repository.findByTamanho(TamanhoSala.MEDIA);
            case "PEQUENA" -> this.repository.findByTamanho(TamanhoSala.PEQUENA);
            default -> new ArrayList<>();
        };
    }

    public List<Sala> findByDisponibilidade(String disponibilidade) {
        if(Objects.equals(disponibilidade.toUpperCase(), "DISPONIVEL"))
            return this.repository.findByDisponibilidade(StatusSala.DISPONIVEL);
        else if (Objects.equals(disponibilidade.toUpperCase(), "INDISPONIVEL"))
            return this.repository.findByDisponibilidade(StatusSala.INDISPONIVEL);

        return new ArrayList<>();
    }

    public Sala findByNome(String nome) {
        return this.repository.findByNome(nome.toUpperCase()).orElse(null);
    }

    public Sala findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Sala create(Sala sala) {
        for(int i=0; i<50; i++) {
            var assento = new Assento();
            assento.setNome(sala.getNome() + (i+1));
            this.assentoService.create(assento);
            sala.getAssentos().add(assento);
        }

        return this.repository.save(sala);
    }

    public Sala update(Sala sala, UUID id) {
        var salaEncontrado = this.findById(id);
        if(salaEncontrado == null)
            return null;

        BeanUtils.copyProperties(sala, salaEncontrado);
        return this.repository.save(salaEncontrado);
    }

    public Boolean delete(UUID id) {
        var sala = this.findById(id);
        if(sala == null)
            return false;

        this.repository.delete(sala);
        return true;
    }
}
