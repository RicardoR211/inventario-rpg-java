package com.meuprojeto.inventario.model.item.armadura;

import com.meuprojeto.inventario.interfaces.Vendavel;
import com.meuprojeto.inventario.model.enums.SlotArmadura;

public class Peitoral extends Armadura implements Vendavel {
    private final double preco;

    public Peitoral(String codigo, String nome, String descricao, int durabilidade, int nivelDefesa, double preco) {
        super(codigo, nome, descricao, durabilidade, nivelDefesa, SlotArmadura.PEITORAL);
        this.preco = preco;
    }

    @Override
    public double getPreco() {
        return this.preco;
    }
}