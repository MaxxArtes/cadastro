package com.example.projetosesp.repository;

import com.example.projetosesp.model.PessoaModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositoryFindByNomeContaining extends JpaRepository<PessoaModel, Integer> {
    public List<PessoaModel> findByNomeContaining(String nome);
}
