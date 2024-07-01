package com.tcc.venda_de_ingressos.repository;

import com.tcc.venda_de_ingressos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.status = false WHERE id=:id")
    void updateStatusFalse(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.status = true WHERE id=:id")
    void updateStatusTrue(@Param("id") UUID id);
}

