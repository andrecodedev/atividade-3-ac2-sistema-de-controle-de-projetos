package com.example.ac2_projetos.dtos_comanda;

public class SetorDTO {
    private String nome; // json de entrada do post /setores

    public SetorDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
