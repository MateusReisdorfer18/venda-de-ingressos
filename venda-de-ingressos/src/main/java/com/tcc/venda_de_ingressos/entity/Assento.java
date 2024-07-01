package com.tcc.venda_de_ingressos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String nome;
    @Enumerated(EnumType.ORDINAL)
    private StatusAssento status = StatusAssento.DISPONIVEL;
}
