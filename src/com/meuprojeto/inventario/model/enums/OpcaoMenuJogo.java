package com.meuprojeto.inventario.model.enums;

public enum OpcaoMenuJogo {
    PROCURAR_ITEM("Procurar/Lootear Item"),
    VER_MOCHILA("Ver Mochila"),
    EQUIPAR_ITEM("Equipar Item da Mochila"),
    USAR_ITEM("Usar Item da Mochila"),
    VER_EQUIPAMENTOS("Ver Itens Equipados"),
    SAIR("Sair do Jogo");

    private final String descricao;

    //Construtor
    OpcaoMenuJogo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
