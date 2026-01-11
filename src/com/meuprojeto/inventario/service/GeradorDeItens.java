package com.meuprojeto.inventario.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.meuprojeto.inventario.model.item.Arma;
import com.meuprojeto.inventario.model.item.Item;
import com.meuprojeto.inventario.model.item.Pocao;
import com.meuprojeto.inventario.model.item.armadura.Capacete;
import com.meuprojeto.inventario.model.item.armadura.Peitoral;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeItens {
    private final List<Item> itensPossiveis = new ArrayList<>();
    private final Random random = new Random();

    /**
    * Verifica a existência do arquivo de configuração.
    * <p>
    Se não tiver o arquivo lá, ele vai soltar um {@code sout} pedindo pra por.
    * Caso contrário, segue o fluxo normal
    */
    public void carregarItensDoArquivo(String caminhoArquivo) {
        // Aq acontece a desserialização
        Gson gson = new Gson();

        /* Tentando ler o json */
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            // Lendo como um array genérico de JSON
            JsonArray array = gson.fromJson(reader, JsonArray.class);

            for (JsonElement elemento : array) {
                JsonObject obj = elemento.getAsJsonObject();

                Item itemCriado = null;

                /* Investigando de que tipo o item é */
                if (obj.has("efeito")) {
                    itemCriado = gson.fromJson(obj, Pocao.class);
                } else if (obj.has("poderDeAtaque")) {
                    itemCriado = gson.fromJson(obj, Arma.class);
                } else if (obj.has("slot")) {
                    // É uma armadura, mas qual?
                    String slot = obj.get("slot").getAsString();
                    if (slot.equals("CABECA")) itemCriado = gson.fromJson(obj, Capacete.class);
                    else if (slot.equals("PEITORAL")) itemCriado = gson.fromJson(obj, Peitoral.class);
                    // ... outros slots ...
                }

                /* Por fim, criando o item*/
                if (itemCriado != null) itensPossiveis.add(itemCriado);
                else System.out.println("Item corrompido ou inválido.");
            }
            System.out.println("Carregados " + itensPossiveis.size() + " " +
                    "tipos de itens para o jogo!");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo\n" +
                    "Por favor, coloque um 'catalogo.json' válido na raiz do seu projeto: " + e.getMessage());
        }
    }

    /**
     * Seleciona e retorna um item aleatório da lista de itens possíveis.
     * @return Um objeto Item escolhido aleatoriamente.
     * @throws IllegalStateException Se a lista estiver vazia. Recomenda-se usar
     * {@link #temItens()} antes de chamar este método.
     */
    public Item gerarItemAleatorio() {
        if (itensPossiveis.isEmpty()) {
            throw new IllegalStateException("A lista de itens está vazia. " +
                    "Execute o carregador de itens antes");
        }

        int indice = random.nextInt(itensPossiveis.size());
        return itensPossiveis.get(indice);
    }

    /**
     * Verifica se a lista de itens possíveis possui algum elemento.
     * <p>
     * Este método deve ser chamado após o carregamento do arquivo para garantir
     * que o gerador está pronto para uso.
     *
     * @return true se houver itens na lista; false se estiver vazia ou não carregada.
     */
    public boolean temItens() {
        return !itensPossiveis.isEmpty();
    }
}

