package com.example.ac2_projetos.dtos_comanda;

import java.time.LocalDate;

public class ProjetoDTO {
    private String descricao;
    private LocalDate dataInicio; // json AAAA-MM-DD, sem hora
    private LocalDate dataFim; // service recusa se for antes do inicio

    public ProjetoDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
