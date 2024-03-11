package com.example.projetosesp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pessoamodel")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PessoaModel {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // [PK]

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 60, message = "O nome deve ter no máximo 60 caracteres")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Size(max = 10, message = "O RG deve ter no máximo 10 caracteres")
    @Column(name = "rg")
    private String rg;

    @NotBlank(message = "O CPF não pode estar em branco")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @NotNull(message = "A data de nascimento não pode estar em branco")
    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Size(max = 11, message = "O telefone deve ter no máximo 11 caracteres")
    @Column(name = "telefone")
    private String telefone;

    @NotBlank(message = "O nome da mãe não pode estar em branco")
    @Size(max = 60, message = "O nome da mãe deve ter no máximo 60 caracteres")
    @Column(name = "nome_mae", unique = true, nullable = false)
    private String nomeMae;

    @Size(max = 60, message = "O nome do pai deve ter no máximo 60 caracteres")
    @Column(name = "nome_pai")
    private String nomePai;

    @NotNull
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pessoa") // Evita recursão infinita ao serializar os endereços
    private List<EnderecoModel> enderecos;

    // Construtor padrão
    public PessoaModel() {
        this.dataCadastro = new Date(); // Define automaticamente a data de cadastro
    }
}
