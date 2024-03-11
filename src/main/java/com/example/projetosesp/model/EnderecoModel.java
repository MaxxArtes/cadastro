package com.example.projetosesp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter // Adiciona automaticamente os métodos getters
@Setter // Adiciona automaticamente os métodos setters
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // [PK]

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @JsonIgnoreProperties("enderecos")
    private PessoaModel pessoa;

    @NotBlank(message = "O logradouro não pode estar em branco")
    @Size(max = 200, message = "O logradouro deve ter no máximo 200 caracteres")
    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @NotBlank(message = "O bairro não pode estar em branco")
    @Size(max = 50, message = "O bairro deve ter no máximo 50 caracteres")
    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "numero")
    private Integer numero;

    @NotBlank(message = "A cidade não pode estar em branco")
    @Size(max = 50, message = "A cidade deve ter no máximo 50 caracteres")
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @NotBlank(message = "O estado não pode estar em branco")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres")
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotBlank(message = "O CEP não pode estar em branco")
    @Size(max = 100, message = "O CEP deve ter no máximo 100 caracteres")
    @Column(name = "cep", nullable = false)
    private String cep;

    // Construtor padrão
    public EnderecoModel() {
    }
}
