package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.Categoria;
import com.tcc.venda_de_ingressos.entity.Classificacao;
import com.tcc.venda_de_ingressos.entity.Filme;
import com.tcc.venda_de_ingressos.repository.FilmeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository repository;

    public List<Filme> findAll() {
        return this.repository.findAll();
    }

    public List<Filme> findByCategoria(Categoria categoria) {
        return this.repository.findByCategoria(categoria);
    }

    public List<Filme> findByClassificacao(String classificacao) {
        var classificacaoFormat = classificacao.toUpperCase();

        return switch (classificacaoFormat) {
            case "L" -> this.repository.findByClassificacao(Classificacao.L);
            case "DEZ" -> this.repository.findByClassificacao(Classificacao.DEZ);
            case "DOZE" -> this.repository.findByClassificacao(Classificacao.DOZE);
            case "QUATORZE" -> this.repository.findByClassificacao(Classificacao.QUATORZE);
            case "DEZESSEIS" -> this.repository.findByClassificacao(Classificacao.DEZESSEIS);
            case "DEZOITO" -> this.repository.findByClassificacao(Classificacao.DEZOITO);
            default -> new ArrayList<>();
        };
    }

    public List<Filme> findByDuracaoLessThanEqual(Integer duracao) {
        return this.repository.findByDuracaoLessThanEqual(duracao);
    }

    public List<Filme> findByDuracaoGreaterThanEqual(Integer duracao) {
        return this.repository.findByDuracaoGreaterThanEqual(duracao);
    }

    public List<Filme> findByDisponibilidadeTrue() {
        return this.repository.findByDisponibilidadeTrue();
    }

    public List<Filme> findByDisponibilidadeFalse() {
        return this.repository.findByDisponibilidadeFalse();
    }

    public Filme findByNome(String nome) {
        return this.repository.findByNome(nome).orElse(null);
    }

    public Filme findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Filme create(Filme filme) {
        return this.repository.save(filme);
    }

    public Filme update(Filme filme, UUID id) {
        var filmeEncontrado = this.findById(id);
        if(filmeEncontrado == null)
            return null;

        BeanUtils.copyProperties(filme, filmeEncontrado);
        return this.repository.save(filmeEncontrado);
    }

    public Boolean delete(UUID id) {
        var filme = this.findById(id);
        if(filme == null)
            return false;

        this.repository.delete(filme);
        return true;
    }
}
