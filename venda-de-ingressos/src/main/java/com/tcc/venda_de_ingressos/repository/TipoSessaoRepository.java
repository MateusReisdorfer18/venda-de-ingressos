package com.tcc.venda_de_ingressos.repository;

import com.tcc.venda_de_ingressos.entity.TipoSessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoSessaoRepository extends JpaRepository<TipoSessao, UUID> {
}
