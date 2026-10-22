package com.example.ac2_projetos.controllers_garcom;

import com.example.ac2_projetos.dtos_comanda.DadosProjetoDTO;
import com.example.ac2_projetos.dtos_comanda.FuncionarioDTO;
import com.example.ac2_projetos.services_cozinha.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // resposta vira json
@RequestMapping("/funcionarios") // só rotas de funcionarios sem new
public class FuncionarioController {

    private final FuncionarioService funcionarioService; // spring injeta o obj sem new

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void adicionar(@RequestBody FuncionarioDTO funcionario) { //corpo json adiciona funcionario
        funcionarioService.adicionar(funcionario);
    }

    @GetMapping("{idFuncionario}/projetos") // idFuncionario igual 1
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable Integer idFuncionario) { // url busca projetos do funcionario
        return funcionarioService.buscarProjetos(idFuncionario); // monte e devolve json
    }
}
