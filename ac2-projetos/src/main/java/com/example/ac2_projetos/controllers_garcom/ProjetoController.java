package com.example.ac2_projetos.controllers_garcom;

import com.example.ac2_projetos.dtos_comanda.DadosProjetoDTO;
import com.example.ac2_projetos.dtos_comanda.ProjetoDTO;
import com.example.ac2_projetos.services_cozinha.ProjetoService;
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
@RequestMapping("/projetos") // só rotas de projetos sem new
public class ProjetoController {

    private final ProjetoService projetoService; // spring injeta o obj sem new

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void adicionar(@RequestBody ProjetoDTO projeto) { // corpo json adiciona projeto
        projetoService.adicionar(projeto);
    }

    @GetMapping
    public List<DadosProjetoDTO> listar() { // monte e devolve json lista de projetos
        return projetoService.listar();
    }

    @GetMapping("{id}")
    public DadosProjetoDTO buscarProjetoPorId(@PathVariable Integer id) { // url busca projeto por id
        return projetoService.buscarProjetoPorId(id);
    }

    @PostMapping("{idProjeto}/funcionarios/{idFuncionario}") // idProjeto igual 1 e idFuncionario igual 1
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void vincularFuncionario(
        @PathVariable Integer idProjeto,
        @PathVariable Integer idFuncionario) { // url
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }
}
