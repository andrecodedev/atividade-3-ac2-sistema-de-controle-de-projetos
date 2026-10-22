package com.example.ac2_projetos.controllers_garcom;

import com.example.ac2_projetos.dtos_comanda.DadosSetorDTO;
import com.example.ac2_projetos.dtos_comanda.SetorDTO;
import com.example.ac2_projetos.services_cozinha.SetorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController // resposta vira json
@RequestMapping("/setores") // só rotas de setores
public class SetorController {

    private final SetorService setorService; // spring injeta o obj sem new

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void adicionar(@RequestBody SetorDTO setor) { //corpo json adiciona setor
        setorService.adicionar(setor);
    }

    @GetMapping("{idSetor}") // idSetor igual 1
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer idSetor) { // url busca setor por id
        return setorService.buscarSetorPorId(idSetor); // monte e devolve json
    }
}
