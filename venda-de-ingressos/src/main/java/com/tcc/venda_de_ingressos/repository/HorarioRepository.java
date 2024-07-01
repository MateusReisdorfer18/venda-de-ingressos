package com.tcc.venda_de_ingressos.repository;

import com.tcc.venda_de_ingressos.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, UUID> {
}
