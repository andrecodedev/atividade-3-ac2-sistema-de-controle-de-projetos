package com.example.ac2_projetos.dtos_comanda;

public class DadosFuncionarioDTO {
    private Integer id; // saida, ja tem id do banco
    private String nome;
    private Integer setorId; // integer pode ser null
    private String setorNome;

    public DadosFuncionarioDTO() {
    }

    public DadosFuncionarioDTO(Integer id, String nome, Integer setorId, String setorNome) {
        this.id = id;
        this.nome = nome;
        this.setorId = setorId;
        this.setorNome = setorNome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }
}
