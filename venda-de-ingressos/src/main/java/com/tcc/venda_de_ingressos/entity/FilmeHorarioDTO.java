package com.tcc.venda_de_ingressos.entity;

import jakarta.validation.Valid;

public record FilmeHorarioDTO(@Valid HorarioSala horarioSala, @Valid Filme filme) {
}
