package com.meuprojeto.inventario.model.item;

import com.meuprojeto.inventario.interfaces.Vendavel;
import com.meuprojeto.inventario.interfaces.Usavel;
import com.meuprojeto.inventario.model.enums.TipoEfeito;

public class Pocao extends Consumivel implements Usavel, Vendavel {
    private final TipoEfeito efeito;
    private final int intensidade;
    private final int preco;

    //O efeito é setado na hora q criam a pocao!
    public Pocao(String codigo, String nome, String descricao, int usosRestantes, TipoEfeito efeito, int intensidade, int preco) {
        super(codigo, nome, descricao, usosRestantes);

        this.efeito = efeito;
        this.intensidade = intensidade;
        this.preco = preco;
    }

    //Permitindo ela ser usável
    @Override
    public void usar() {
        if (getUsosRestantes() > 0) {
            // Ativando a poção
            this.efeito.aplicar(getIntensidade());
            decrementarUsos(); // Diminui a durabilidade da pot
            System.out.println("Usos restantes: " + getUsosRestantes());
        } else {
            System.out.println("Esta poção já foi usada.");
        }
    }

    public TipoEfeito getEfeito() {
        return this.efeito;
    }

    @Override
    public double getPreco() {
        return this.preco;
    }

    public int getIntensidade() { return this.intensidade; }
}
