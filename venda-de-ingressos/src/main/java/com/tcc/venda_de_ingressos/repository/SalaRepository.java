package com.tcc.venda_de_ingressos.repository;

import com.tcc.venda_de_ingressos.entity.Sala;
import com.tcc.venda_de_ingressos.entity.StatusSala;
import com.tcc.venda_de_ingressos.entity.TamanhoSala;
import com.tcc.venda_de_ingressos.entity.TipoSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaRepository extends JpaRepository<Sala, UUID> {
    Optional<Sala> findByNome(String nome);
    List<Sala> findByTipo(TipoSala tipo);
    List<Sala> findByTamanho(TamanhoSala tamanho);
    List<Sala> findByDisponibilidade(StatusSala disponibilidade);

}
