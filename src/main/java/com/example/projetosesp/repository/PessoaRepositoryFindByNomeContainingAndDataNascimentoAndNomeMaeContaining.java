package com.example.projetosesp.repository;

import com.example.projetosesp.model.PessoaModel;

import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PessoaRepositoryFindByNomeContainingAndDataNascimentoAndNomeMaeContaining
        extends JpaRepository<PessoaModel, Integer> {

    @Query("""
            SELECT p FROM PessoaModel p
            WHERE
               LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND
               LOWER(p.nomeMae) LIKE LOWER(CONCAT('%', :nomeMae, '%')) AND
               p.dataNascimento = :dataNascimento
            """)
    List<PessoaModel> findByNomeEDataNascimentoENomeMae(@Param("nome") String nome,
            @Param("dataNascimento") Date dataNascimento,
            @Param("nomeMae") String nomeMae);

    @Query("""
            SELECT p FROM PessoaModel p
            WHERE
               LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR
               LOWER(p.nomeMae) LIKE LOWER(CONCAT('%', :nomeMae, '%')) OR
               p.dataNascimento = :dataNascimento
            """)
    List<PessoaModel> findByNomeOuDataNascimentoOuNomeMae(@Param("nome") String nome,
            @Param("dataNascimento") Date dataNascimento,
            @Param("nomeMae") String nomeMae);
}