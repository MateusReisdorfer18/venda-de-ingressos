package com.tcc.venda_de_ingressos.repository;

import com.tcc.venda_de_ingressos.entity.Filme;
import com.tcc.venda_de_ingressos.entity.Horario;
import com.tcc.venda_de_ingressos.entity.HorarioSala;
import com.tcc.venda_de_ingressos.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HorarioSalaRepository extends JpaRepository<HorarioSala, UUID> {
    @Query("SELECT hs FROM HorarioSala hs WHERE hs.sala = :SALA AND hs.horario = :HORARIO")
    Optional<HorarioSala> consultDisponibilidade(@Param("SALA") Sala sala, @Param("HORARIO")Horario horario);
    List<HorarioSala> findBySala(Sala sala);
    List<HorarioSala> findBySalaAndDisponibilidade(Sala sala, Boolean disponibilidade);
    @Modifying
    @Transactional
    @Query("UPDATE HorarioSala hs SET hs.disponibilidade = :DISPONIBILIDADE WHERE hs.sala = :SALA AND hs.horario = :HORARIO")
    void updateDisponibilidade(@Param("SALA") Sala sala, @Param("HORARIO") Horario horario, @Param("DISPONIBILIDADE") boolean disponibilidade);
}
