package com.tcc.venda_de_ingressos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Classificacao {
    L("L"),
    DEZ("DEZ"),
    DOZE("DOZE"),
    QUATORZE("QUATORZE"),
    DEZESSEIS("DEZESSEIS"),
    DEZOITO("DEZOITO");

    private final String tipo;
}
