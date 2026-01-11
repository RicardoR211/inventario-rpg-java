package com.meuprojeto.inventario.model.item;

public abstract class Consumivel extends Item {
    private int usosRestantes;

    public Consumivel(String codigo, String nome, String descricao, int usosRestantes) {
        super(codigo, nome, descricao);

        this.usosRestantes = usosRestantes;
    }

    public int getUsosRestantes() {
        return usosRestantes;
    }

    /* Para usar item */
    public abstract void usar();

    /* Decrementa os usos */
    protected void decrementarUsos() {
        if(this.usosRestantes > 0) {
            this.usosRestantes--;
        }
    }
}
