package com.example.ac2_projetos.dtos_comanda;

public class FuncionarioDTO {
    private String nome;
    private Integer setorId; // integer pode ser null

    public FuncionarioDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSetorId() {
        return setorId;
    }

    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }
}
