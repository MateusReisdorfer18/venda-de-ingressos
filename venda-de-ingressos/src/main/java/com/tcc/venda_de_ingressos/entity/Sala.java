package com.tcc.venda_de_ingressos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @NotNull
    private String nome;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TipoSala tipo;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TamanhoSala tamanho;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private StatusSala disponibilidade = StatusSala.DISPONIVEL;
}
