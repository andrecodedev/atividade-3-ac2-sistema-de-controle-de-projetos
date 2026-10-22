package com.example.ac2_projetos.dtos_comanda;

import java.util.Arrays;
import java.util.List;

public class ApiErrorDTO {
    private List<String> errors; // { "errors": ["mensagem"] }

    public ApiErrorDTO(String mensagem) {
        this.errors = Arrays.asList(mensagem);
    }

    public ApiErrorDTO(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
