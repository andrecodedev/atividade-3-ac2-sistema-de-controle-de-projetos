package com.example.ac2_projetos.services_cozinha;

import com.example.ac2_projetos.dtos_comanda.DadosFuncionarioDTO;
import com.example.ac2_projetos.dtos_comanda.DadosProjetoDTO;
import com.example.ac2_projetos.dtos_comanda.ProjetoDTO;
import com.example.ac2_projetos.config_gerente.exceptions_reclamacao.RegraNegocioException;
import com.example.ac2_projetos.models_cardapio.Funcionario;
import com.example.ac2_projetos.models_cardapio.Projeto;
import com.example.ac2_projetos.repositories_despensa.FuncionarioRepository;
import com.example.ac2_projetos.repositories_despensa.ProjetoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoService(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public void adicionar(ProjetoDTO dto) {
        validar(dto);
        Projeto projeto = new Projeto(dto.getDescricao().trim(), dto.getDataInicio(), dto.getDataFim());
        projetoRepository.save(projeto);
    }

    @Transactional(readOnly = true)
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.buscarProjetoComFuncionarios(id)
            .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado com o ID " + id));
        return toDados(projeto);
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> listar() {
        return projetoRepository.buscarTodosComFuncionarios().stream()
            .map(this::toDados)
            .collect(Collectors.toList());
    }

    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.buscarProjetoComFuncionarios(idProjeto)
            .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado com o ID " + idProjeto));
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
            .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado com o ID " + idFuncionario));

        boolean jaVinculado = projeto.getFuncionarios().stream()
            .anyMatch(f -> f.getId().equals(idFuncionario));
        if (jaVinculado) {
            throw new RegraNegocioException("Funcionário já está vinculado a este projeto.");
        }

        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }

    private void validar(ProjetoDTO dto) {
        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new RegraNegocioException("A descrição do projeto é obrigatória.");
        }
        if (dto.getDataInicio() == null) {
            throw new RegraNegocioException("A data de início é obrigatória.");
        }
        if (dto.getDataFim() == null) {
            throw new RegraNegocioException("A data de fim é obrigatória.");
        }
        if (dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new RegraNegocioException("A data de fim não pode ser anterior à data de início.");
        }
    }

    DadosProjetoDTO toDados(Projeto projeto) {
        List<DadosFuncionarioDTO> funcionarios = projeto.getFuncionarios() == null
            ? List.of()
            : projeto.getFuncionarios().stream().map(this::toFuncionario).collect(Collectors.toList());

        return new DadosProjetoDTO(
            projeto.getId(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            funcionarios);
    }

    private DadosFuncionarioDTO toFuncionario(Funcionario funcionario) {
        return new DadosFuncionarioDTO(
            funcionario.getId(),
            funcionario.getNome(),
            funcionario.getSetor() != null ? funcionario.getSetor().getId() : null,
            funcionario.getSetor() != null ? funcionario.getSetor().getNome() : null);
    }
}
