package com.meuprojeto.inventario.model.item.armadura;

import com.meuprojeto.inventario.model.item.Equipamento;
import com.meuprojeto.inventario.model.enums.SlotArmadura; //Pegando as posicoes de armadura

public abstract class Armadura extends Equipamento {
    private final int nivelDefesa;

    private final SlotArmadura slot;

    public Armadura(String codigo, String nome, String descricao, int durabilidade, int nivelDefesa, SlotArmadura slot) {
        super(codigo, nome, descricao, durabilidade);

        this.nivelDefesa = nivelDefesa;
        this.slot = slot;
    }

    /* Getters */
    public int getNivelDefesa() {
        return nivelDefesa;
    }

    public SlotArmadura getSlot() {
        return slot;
    }
}
