package com.tcc.venda_de_ingressos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioSala {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @ManyToOne
    @NotNull
    private Horario horario;
    @ManyToOne
    @NotNull
    private Sala sala;
    @ManyToOne
    private Filme filme;
    private boolean disponibilidade;
}
