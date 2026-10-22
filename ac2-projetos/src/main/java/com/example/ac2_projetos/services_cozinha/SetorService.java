package com.example.ac2_projetos.services_cozinha;

import com.example.ac2_projetos.dtos_comanda.DadosFuncionarioDTO;
import com.example.ac2_projetos.dtos_comanda.DadosSetorDTO;
import com.example.ac2_projetos.dtos_comanda.SetorDTO;
import com.example.ac2_projetos.config_gerente.exceptions_reclamacao.RegraNegocioException;
import com.example.ac2_projetos.models_cardapio.Setor;
import com.example.ac2_projetos.repositories_despensa.SetorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // crachá cria o bean e injeta no controller
public class SetorService {

    private final SetorRepository setorRepository; // fala com o banco

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Transactional // gravação, salva tudo ou nao
    public void adicionar(SetorDTO dto) { // adiciona setor no banco
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException("O nome do setor é obrigatório.");
        }
        setorRepository.save(new Setor(dto.getNome().trim()));
    }

    @Transactional(readOnly = true)
    public DadosSetorDTO buscarSetorPorId(Integer idSetor) { // busca setor por id no banco
        Setor setor = setorRepository.buscarPorIdComFuncionarios(idSetor)
            .orElseThrow(() -> new RegraNegocioException("Setor não encontrado com o ID " + idSetor));
        return toDados(setor);
    }

    private DadosSetorDTO toDados(Setor setor) { // monta json setor com funcionarios
        List<DadosFuncionarioDTO> funcionarios = setor.getFuncionarios() == null
            ? List.of()
            : setor.getFuncionarios().stream()
                .map(f -> new DadosFuncionarioDTO(f.getId(), f.getNome(), setor.getId(), setor.getNome()))
                .collect(Collectors.toList());

        return new DadosSetorDTO(setor.getId(), setor.getNome(), funcionarios);
    }
}
