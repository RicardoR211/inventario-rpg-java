package com.meuprojeto.inventario.model.item;

public abstract class Item {
    private String nome;
    private String descricao;
    private final String codigo;

    //Criando o construtor
    public Item(String codigo, String nome, String descricao){
        //System.out.println("O seu livro está sendo criado!");
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    //Formatando como os itens serão mostrados
    @Override
    public String toString() {
        return codigo + " " + nome + " " + descricao;
    }

    // Getters para acessar os dados
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
