package com.example.projetosesp.repository;

import com.example.projetosesp.model.PessoaModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositoryFindByCpf extends JpaRepository<PessoaModel, Integer> {
    public Optional<PessoaModel> findByCpf(String cpf);
}
