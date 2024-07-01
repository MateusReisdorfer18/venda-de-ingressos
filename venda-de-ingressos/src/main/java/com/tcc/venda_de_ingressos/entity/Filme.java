package com.tcc.venda_de_ingressos.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @NotNull
    private String nome;
    @ManyToOne
    @NotNull
    private Categoria categoria;
    @NotNull
    private Integer duracao;
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Classificacao classificacao;
    @Nullable
    private String descricao;
    @NotNull
    private LocalDate dataEstreia;
    private boolean disponibilidade = true;
    @Nullable
    private String posterUrl;
}
