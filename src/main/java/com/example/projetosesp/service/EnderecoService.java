package com.example.projetosesp.service;

import com.example.projetosesp.model.EnderecoModel;
import com.example.projetosesp.repository.EnderecoRepository;
import com.example.projetosesp.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<EnderecoModel> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public EnderecoModel getEnderecoById(int id) {
        Optional<EnderecoModel> enderecoOptional = enderecoRepository.findById(id);
        return enderecoOptional.orElse(null);
    }

    public EnderecoModel createEndereco(EnderecoModel enderecoModel) {
        // Verifica se o ID da pessoa existe
        int pessoaId = enderecoModel.getPessoa().getId();
        if (pessoaRepository.existsById(pessoaId)) {
            return enderecoRepository.save(enderecoModel);
        } else {
            throw new IllegalArgumentException("ID da pessoa não encontrado: " + pessoaId);
        }
    }

    public EnderecoModel updateEndereco(int id, EnderecoModel enderecoModel) {
        if (enderecoRepository.existsById(id)) {
            enderecoModel.setId(id);
            return enderecoRepository.save(enderecoModel);
        } else {
            return null;
        }
    }

    public void deleteEndereco(int id) {
        enderecoRepository.deleteById(id);
    }
}
