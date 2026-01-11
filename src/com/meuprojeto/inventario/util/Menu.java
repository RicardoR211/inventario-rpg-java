package com.meuprojeto.inventario.util;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu(){
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu(String[] opcoes) {
        System.out.println("\n--- Escolha uma opcao ---");
        for(String opcao : opcoes){
            System.out.println(opcao);
        }
        System.out.println("--------------------------");
    }

    public int lerOpcao(int min, int max) {
        int opcao = -1;
        while (true) {
            System.out.print("Digite sua escolha (" + min + "-" + max + "): ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao >= min && opcao <= max) {
                    return opcao;
                } else {
                    System.out.println("Erro: Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
            }
        }
    }

    public int lerInteiro(String prompt) {
        int valor = 0;
        while (true) {
            System.out.print(prompt);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número inteiro válido.");
            }
        }
    }

    public double lerDouble(String prompt) {
        double valor = 0.0;
        while (true) {
            System.out.print(prompt);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato inválido. Use '.' como separador decimal.");
            }
        }
    }

    /* Usando Method Overloadin para adicionarr um valor máximo de caracteres como recurso opcional */
    public String lerTexto(String prompt) {
        //Se n escolher nada ele vai como -1
        return lerTexto(prompt, -1);
    }

    /* Tratando a quantia maxima de caracteres e não deixando ser nulo */
    public String lerTexto(String prompt, int tamanhoMaximo) {
        String texto;

        while(true) {
            System.out.print(prompt);
            texto = scanner.nextLine();

            /* Verificando se n ta vazio */
            if(texto.trim().isEmpty()) {
                System.out.println("Erro: A entrada não pode ser vazia");
                continue;
            }

            /* Verificando se é maior que o valor máximo */
            /* Só executa se o valor máximo enviado for maior que -1 */
            if (tamanhoMaximo > 0 && texto.length() > tamanhoMaximo) {
                System.out.println("Erro: A entrada não pode ter mais que " + tamanhoMaximo + " caracteres.");
                continue;
            }

            //Sai do while se passar de todos os ifs
            break;
        }

        return texto;
    }

    public void fechar() {
        this.scanner.close();
    }
}
