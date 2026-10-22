package com.example.ac2_projetos.config_gerente;

import com.example.ac2_projetos.dtos_comanda.ApiErrorDTO;
import com.example.ac2_projetos.config_gerente.exceptions_reclamacao.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDTO handleRegraNegocioException(RegraNegocioException ex) {
        return new ApiErrorDTO(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDTO handleJsonInvalido(HttpMessageNotReadableException ex) {
        return new ApiErrorDTO("JSON inválido. Confira datas no formato AAAA-MM-DD e os campos obrigatórios.");
    }
}
