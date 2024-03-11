package com.example.projetosesp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projetosesp.model.PessoaModel;

public interface PessoaRepositoryPageble extends JpaRepository<PessoaModel, Long> {
    Page<PessoaModel> findAllByOrderByNomeAsc(Pageable pageable);
}
