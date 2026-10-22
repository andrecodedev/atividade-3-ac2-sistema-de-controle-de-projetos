package com.example.ac2_projetos.services_cozinha;

import com.example.ac2_projetos.dtos_comanda.DadosProjetoDTO;
import com.example.ac2_projetos.dtos_comanda.FuncionarioDTO;
import com.example.ac2_projetos.config_gerente.exceptions_reclamacao.RegraNegocioException;
import com.example.ac2_projetos.models_cardapio.Funcionario;
import com.example.ac2_projetos.models_cardapio.Projeto;
import com.example.ac2_projetos.models_cardapio.Setor;
import com.example.ac2_projetos.repositories_despensa.FuncionarioRepository;
import com.example.ac2_projetos.repositories_despensa.ProjetoRepository;
import com.example.ac2_projetos.repositories_despensa.SetorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    private final ProjetoRepository projetoRepository;
    private final ProjetoService projetoService;

    public FuncionarioService(
            FuncionarioRepository funcionarioRepository,
            SetorRepository setorRepository,
            ProjetoRepository projetoRepository,
            ProjetoService projetoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
        this.projetoRepository = projetoRepository;
        this.projetoService = projetoService;
    }

    @Transactional
    public void adicionar(FuncionarioDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException("O nome do funcionário é obrigatório.");
        }
        if (dto.getSetorId() == null) {
            throw new RegraNegocioException("O setor do funcionário é obrigatório.");
        }
        Setor setor = setorRepository.findById(dto.getSetorId())
            .orElseThrow(() -> new RegraNegocioException("Setor não encontrado com o ID " + dto.getSetorId()));

        Funcionario funcionario = new Funcionario(dto.getNome().trim());
        funcionario.setSetor(setor);
        funcionarioRepository.save(funcionario);
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RegraNegocioException("Funcionário não encontrado com o ID " + idFuncionario);
        }
        List<Projeto> projetos = funcionarioRepository.buscarProjetosPorFuncionario(idFuncionario);
        return projetos.stream()
            .map(p -> projetoRepository.buscarProjetoComFuncionarios(p.getId()).orElse(p))
            .map(projetoService::toDados)
            .collect(Collectors.toList());
    }
}
