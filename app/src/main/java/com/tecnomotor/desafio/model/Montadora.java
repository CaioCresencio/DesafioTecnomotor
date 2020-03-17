package com.tecnomotor.desafio.model;

public class Montadora {

    private int id;
    private String nome;
    private String tipo;

    public Montadora(int id, String nome, String tipo){
        this.setId(id);
        this.setNome(nome);
        this.setTipo(tipo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
