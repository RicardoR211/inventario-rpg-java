package com.meuprojeto.inventario.model.item.armadura;

import com.meuprojeto.inventario.interfaces.Vendavel;
import com.meuprojeto.inventario.model.enums.SlotArmadura;

public class Calca extends Armadura implements Vendavel {
    private final double preco;

    public Calca(String codigo, String nome, String descricao, int durabilidade, int nivelDefesa, double preco) {
        super(codigo, nome, descricao, durabilidade, nivelDefesa, SlotArmadura.CALCAS);
        this.preco = preco;
    }

    @Override
    public double getPreco() {
        return this.preco;
    }
}
