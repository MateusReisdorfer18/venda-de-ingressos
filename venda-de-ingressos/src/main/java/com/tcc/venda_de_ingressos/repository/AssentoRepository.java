package com.tcc.venda_de_ingressos.repository;

import com.tcc.venda_de_ingressos.entity.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE Assento a SET a.status = RESERVADO WHERE a.id = :id")
    void reserveAssento(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Assento a SET a.status = DISPONIVEL WHERE a.id = :id")
    void releaseAssento(@Param("id") UUID id);

    @Query("SELECT a FROM Assento a WHERE a.id = :id AND a.status = DISPONIVEL")
    Optional<Assento> consultDisponibilidade(@Param("id") UUID id);
}
