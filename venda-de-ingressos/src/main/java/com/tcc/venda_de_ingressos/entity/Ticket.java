package com.tcc.venda_de_ingressos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @ManyToOne
    @NotNull
    private Filme filme;
    @ManyToOne
    @NotNull
    private Sala sala;
    private LocalDateTime horaCompra = LocalDateTime.now();
    @NotNull
    @ManyToOne
    private Horario horario;
    @ManyToOne
    @NotNull
    private Assento assento;
}
