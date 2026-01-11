package com.meuprojeto.inventario.model.jogador;

import com.meuprojeto.inventario.interfaces.Usavel;
import com.meuprojeto.inventario.model.enums.SlotArmadura;
import com.meuprojeto.inventario.model.item.Consumivel;
import com.meuprojeto.inventario.model.item.Item;
import com.meuprojeto.inventario.model.item.armadura.Armadura;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogador {
    private String nome;
    private float vida;
    private int dinheiro;
    private final List<Item> mochila = new ArrayList<>();
    private final Map<SlotArmadura, Armadura> armaduraEquipada = new HashMap<>();

    public Jogador(String nome, float vida, int dinheiro) {
        this.nome = nome;
        this.vida = vida;
        this.dinheiro = dinheiro;
    }

    public Jogador(String nome) {
        this(nome, 100.0f, 250);
    }

    /* Getters */
    public String getNome() {
        // Apernas retorna o nome do player :P
        return nome;
    }

    public List<Item> getMochila(){
        return mochila;
    }

    /**
     * Verifica se há algum item na mochila
     * @return True se tiver algo, false se não houver*/
    public boolean getMochilaTemAlgo(){
        return !mochila.isEmpty();
    }

    /**
     * Retorna a quantidade de itens (mochila.size)
     */
    public int getMochilaQuantidadeItens() {
        return mochila.size();
    }

    /**
     * Retorna o item na posição dada
     * @return Retorna o item para index válido, do contrario retorna null*/
    public Item getItemDaMochila(int index) {
        if(index >= 0 && index < mochila.size()) {
            return mochila.get(index);
        }
        return null;
    }

    /* Retorna a mochila ja formatada no terminal */
    public void mostrarMochila() {
        System.out.println("\n--- Mochila de " + this.nome + " ---");
        if (mochila.isEmpty()) {
            System.out.println("(Vazia)");
        } else {
            for (int i = 0; i < mochila.size(); i++) {
                Item item = mochila.get(i);
                // Aqui você pode adicionar lógica extra, tipo mostrar (Equipado) se fosse o caso
                System.out.println((i + 1) + ". " + item.getNome());
            }
        }
        System.out.println("---------------------------");
    }

    public void mostrarEquipamentos() {
        System.out.println("\n--- Equipamentos de " + nome + " ---");

        for (SlotArmadura slot : SlotArmadura.values()) {
            if (armaduraEquipada.containsKey(slot)) {
                // TEM ITEM: Mostra os detalhes bonitinhos
                Armadura item = armaduraEquipada.get(slot);
                System.out.println(slot + ": " + item.getNome() + " [Defesa: " + item.getNivelDefesa() + "]");
            } else {
                // NÃO TEM ITEM: Mostra vazio
                System.out.println(slot + ": (Vazio)");
            }
        }
    }

    /* Criando métodos */
    public void pegarItem(Item item) {
        if(item != null) {
            mochila.add(item);
            System.out.println(nome + " pegou o item " + item.getNome());
        }
    }

    // Equipando itens
    public void equiparItem(@NotNull Armadura armaduraParaEquipar) {
        // Pegando o slot onde fica a armadura
        SlotArmadura slot = armaduraParaEquipar.getSlot();

        // Verificando se ja tem algo equipado lá
        if(armaduraEquipada.containsKey(slot)) {
            Armadura armaduraAntiga = armaduraEquipada.get(slot);

            // Se tiver, a gente desequipa a antiga e equipa a nova
            mochila.add(armaduraAntiga);
            System.out.println("-> Desequipou " + armaduraAntiga.getNome());
        }

        // Equipando novo item
        armaduraEquipada.put(slot, armaduraParaEquipar);

        // Removendo da mochila, ja que agora ele está no corpo
        mochila.remove(armaduraParaEquipar);

        System.out.println("-> Equipou " + armaduraParaEquipar.getNome());
    }

    /* Usando item */
    public void usarItemDaMochila(int indice) {
        if(indice >= 0 && indice < mochila.size()) {
            Item item = mochila.get(indice);

            if(item instanceof Usavel usavelItem) {
                //Chama o metodo usar lá definido no item
                usavelItem.usar();

                // Definindo a lógica para consumir o item se ele for um Consumível
                if (item instanceof Consumivel consumivel) {
                    if(consumivel.getUsosRestantes() <= 0) {
                        //Removendo item da mochila pois os usos acabaram
                        mochila.remove(item);
                        System.out.println("O item '" + item.getNome() + "' acabou e foi removido" +
                                "da mochila.");
                    }
                }
            } else {
                /* Se o item não pode ser usado */
                System.out.println("O item '" + item.getNome() + "' não pode ser usado");
            }
        } else {
            /* Fora do escopo de itens */
            System.out.println("Opção inválida. Por favor, selecione um número da lista.");
        }
    }
}
