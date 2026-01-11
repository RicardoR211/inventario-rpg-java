package com.meuprojeto.inventario;

import com.meuprojeto.inventario.model.enums.OpcaoMenuJogo;
import com.meuprojeto.inventario.model.item.Item;
import com.meuprojeto.inventario.model.item.armadura.Armadura;
import com.meuprojeto.inventario.model.jogador.Jogador;
import com.meuprojeto.inventario.service.GeradorDeItens;
import com.meuprojeto.inventario.util.Menu;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /* Instâncias estáticas para usar nos métodos auxiliares */
    private static final GeradorDeItens gerador = new GeradorDeItens();
    private static final Menu menu = new Menu();
    private static Jogador jogadorAtual;

    public static void main(String[] args) {
        /* Iniciando */
        System.out.println("Iniciando sistema...");
        gerador.carregarItensDoArquivo("catalogo.json");

        // Criação dos jogadores
        List<Jogador> jogadores = new ArrayList<>();
        String nomeJogador = menu.lerTexto("Digite o nome do seu Herói: ");
        Jogador player1 = new Jogador(nomeJogador);
        jogadores.add(player1);

        // Definindo quem está jogando agora
        jogadorAtual = player1;

        /* Preparando o Menu via Enum */
        OpcaoMenuJogo[] opcoesEnum = OpcaoMenuJogo.values();
        String[] textoMenu = new String[opcoesEnum.length];
        for (OpcaoMenuJogo opcao : opcoesEnum) {
            textoMenu[opcao.ordinal()] = (opcao.ordinal() + 1) + " - " + opcao.getDescricao();
        }

        /* Loop do jogo */
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== AVENTURA DE " + jogadorAtual.getNome().toUpperCase() + " ===");
            menu.exibirMenu(textoMenu);

            int escolha = menu.lerOpcao(1, textoMenu.length);
            OpcaoMenuJogo opcaoEscolhida = opcoesEnum[escolha - 1];

            switch (opcaoEscolhida) {
                case PROCURAR_ITEM:
                    tratarProcurarItem();
                    break;
                case VER_MOCHILA:
                    jogadorAtual.mostrarMochila();
                    break;
                case EQUIPAR_ITEM:
                    tratarEquiparItem();
                    break;
                case USAR_ITEM:
                    tratarUsarItem();
                    break;
                case VER_EQUIPAMENTOS:
                    jogadorAtual.mostrarEquipamentos();
                    break;
                case SAIR:
                    System.out.println("Saindo!");
                    menu.fechar();
                    rodando = false;
                    break;
            }
        }
    }

    /* Métodos auxiliares */
    private static void tratarProcurarItem() {
        System.out.println("\nProcurando nos arredores...");

        if (gerador.temItens() == false) {
            System.out.println("\nNão houve nada para ser encontrado, o catálogo está vazio.");
            return;
        }

        //Encontrando item
        Item itemEncontrado = gerador.gerarItemAleatorio();
        System.out.println("\nVocê escontrou: " + itemEncontrado.getNome());
        System.out.println("Descrição: " + itemEncontrado.getDescricao());

        String resposta = menu.lerTexto("\nDeseja pegar? (s/n): ");
        if(resposta.equalsIgnoreCase("s")){
            jogadorAtual.pegarItem(itemEncontrado);
        } else {
            System.out.println("\nVocê deixou o item no chão");
        }
    }

    private static void tratarEquiparItem(){
        if(jogadorAtual.getMochilaTemAlgo() == false) {
            System.out.println("\nNão há itens na sua mochila para ser equipado!");
            return;
        }

        jogadorAtual.mostrarMochila();

        int index = menu.lerInteiro("Digite o número do item para equipar (0 para cancelar): ");
        if (index == 0) return;

        //Validar índice e pegar item
        if (index > 0 && index <= jogadorAtual.getMochilaQuantidadeItens()){
            Item item = jogadorAtual.getItemDaMochila(index - 1);

            //Tratando equipamento
            if(item instanceof Armadura armadura) {
                jogadorAtual.equiparItem(armadura);
            } else {
                System.out.println("Este item não é uma armadura e não pode ser equipado.");
            }
        } else {
            System.out.println("\nNúmero inválido.");
        }
    }

    private static void tratarUsarItem(){
        if(jogadorAtual.getMochilaTemAlgo() == false) {
            System.out.println("\nNão a nada para ser usado.");
            return;
        }
        jogadorAtual.mostrarMochila();

        int index = menu.lerInteiro("Digite o número do item para usar (0 para cancelar): ");
        if (index == 0) return;

        //O nosso jogador que trata da validação do índice e do tipo Usavel.
        jogadorAtual.usarItemDaMochila(index - 1);

    }
}
