package com.example.projetosesp.service;

import com.example.projetosesp.model.PessoaModel;
import com.example.projetosesp.repository.PessoaRepository;
import com.example.projetosesp.repository.PessoaRepositoryFindByCpf;
import com.example.projetosesp.repository.PessoaRepositoryFindByNomeContaining;
import com.example.projetosesp.repository.PessoaRepositoryFindByNomeContainingAndDataNascimentoAndNomeMaeContaining;
import com.example.projetosesp.repository.PessoaRepositoryPageble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepositoryFindByCpf pessoaRepositoryFindByCpf;

    @Autowired
    private PessoaRepositoryFindByNomeContaining pessoaRepositoryFindByNomeContaining;

    @Autowired
    private PessoaRepositoryFindByNomeContainingAndDataNascimentoAndNomeMaeContaining pessoaRepositoryFindByNomeContainingAndDataNascimentoAndNomeMaeContaining;

    @Autowired
    private PessoaRepositoryPageble pessoaRepositoryPageble;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaModel> findByNomeContainingAndDataNascimentoAndNomeMaeContaining(String nome, Date dataNascimento,
            String nomeMae) {
        if (nome != null && dataNascimento != null && nomeMae != null)
            return pessoaRepositoryFindByNomeContainingAndDataNascimentoAndNomeMaeContaining
                    .findByNomeEDataNascimentoENomeMae(nome, dataNascimento, nomeMae);
        return pessoaRepositoryFindByNomeContainingAndDataNascimentoAndNomeMaeContaining
                .findByNomeOuDataNascimentoOuNomeMae(nome, dataNascimento, nomeMae);
    }

    public List<PessoaModel> getAllPessoasWithoutPagination() {
        return pessoaRepository.findAll();
    }

    @SuppressWarnings("null")
    public Page<PessoaModel> getAllPessoas(Pageable pageable) {
        return pessoaRepositoryPageble.findAll(pageable);
    }

    public Optional<PessoaModel> getPessoaByCpf(String cpf) {
        return pessoaRepositoryFindByCpf.findByCpf(cpf);
    }
    

    public Optional<PessoaModel> getPessoaById(int id) {
        // Aqui você pode escolher qual repositório usar para buscar por ID
        // Vou usar um deles como exemplo
        return pessoaRepositoryFindByCpf.findById(id);
    }

    public List<PessoaModel> saveOrUpdatePessoas(List<PessoaModel> pessoas) {
        List<PessoaModel> savedPessoas = new ArrayList<>();
        for (PessoaModel pessoa : pessoas) {
            @SuppressWarnings("null")
            PessoaModel savedPessoa = pessoaRepositoryFindByCpf.save(pessoa);
            savedPessoas.add(savedPessoa);
        }
        return savedPessoas;
    }

    public void deletePessoa(int id) {
        pessoaRepositoryFindByCpf.deleteById(id);
    }

    // Método para salvar ou atualizar uma única pessoa
    @SuppressWarnings("null")
    public PessoaModel saveOrUpdatePessoa(PessoaModel pessoa) {
        return pessoaRepositoryFindByCpf.save(pessoa);
    }

    public List<PessoaModel> findByNome(String nome) {
        return pessoaRepositoryFindByNomeContaining.findByNomeContaining(nome);
    }

    // Outros métodos conforme necessário para cada tipo de consulta
}
