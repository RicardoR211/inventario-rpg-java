package com.meuprojeto.inventario.model.item;

import com.meuprojeto.inventario.interfaces.Vendavel;

public class Arma extends Equipamento implements Vendavel {
    private final int poderDeAtaque;
    private final double preco;

    public Arma(String codigo, String nome, String descricao, int durabilidade, int poderDeAtaque, double preco) {
        super(codigo, nome, descricao, durabilidade);
        this.poderDeAtaque = poderDeAtaque;
        this.preco = preco;
    }

    /* Getters */
    public int getPoderDeAtaque() {
        return poderDeAtaque;
    }

    /* Implements */
    @Override
    public double getPreco() {
        return preco;
    }
}