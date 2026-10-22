package com.example.ac2_projetos.repositories_despensa;

import com.example.ac2_projetos.models_cardapio.Funcionario;
import com.example.ac2_projetos.models_cardapio.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT p FROM Projeto p JOIN p.funcionarios f WHERE f.id = :idFuncionario")
    List<Projeto> buscarProjetosPorFuncionario(@Param("idFuncionario") Integer idFuncionario);
}
