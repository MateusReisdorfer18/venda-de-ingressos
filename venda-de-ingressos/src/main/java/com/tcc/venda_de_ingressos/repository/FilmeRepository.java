package com.tcc.venda_de_ingressos.repository;

import com.tcc.venda_de_ingressos.entity.Categoria;
import com.tcc.venda_de_ingressos.entity.Classificacao;
import com.tcc.venda_de_ingressos.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, UUID> {
    List<Filme> findByCategoria(Categoria categoria);
    List<Filme> findByClassificacao(Classificacao classificacao);
    List<Filme> findByDuracaoLessThanEqual(Integer duracao);
    List<Filme> findByDuracaoGreaterThanEqual(Integer duracao);
    Optional<Filme> findByNome(String nome);
    List<Filme> findByDisponibilidadeTrue();
    List<Filme> findByDisponibilidadeFalse();
}
