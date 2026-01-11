package com.meuprojeto.inventario.model.item;

public abstract class Equipamento extends Item{
    private int durabilidade;

    public Equipamento(String codigo, String nome, String descricao, int durabilidade) {
        super(codigo, nome, descricao);

        this.durabilidade = durabilidade;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }
}
