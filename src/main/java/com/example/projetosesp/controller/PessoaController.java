package com.example.projetosesp.controller;

import com.example.projetosesp.model.PessoaModel;
import com.example.projetosesp.service.PessoaService;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
@Tag(name = "Pessoas", description = "Endpoints para gerenciar pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Getter
    @Setter
    public static class PageResponse {
        @JsonProperty("Elementos")
        private List<PessoaModel> content;

        @JsonProperty("NumerodaPagina")
        private int pageNumber;

        @JsonProperty("TotaldePaginas")
        private int totalPages;

        @JsonProperty("TotaldeElementos")
        private long totalElements;

        public PageResponse(List<PessoaModel> content, int pageNumber, int totalPages, long totalElements) {
            this.content = content;
            this.pageNumber = pageNumber + 1;
            this.totalPages = totalPages;
            this.totalElements = totalElements;
        }
    }

    @Operation(
        summary = "Buscar todas as pessoas",
        description = "Retorna uma lista paginada de todas as pessoas.",
        tags = {"Pessoas", "get"})
    @ApiResponse(responseCode = "200", description = "Pessoas encontradas")
    @ApiResponse(responseCode = "204", description = "Nenhuma pessoa encontrada")
    @GetMapping
    public ResponseEntity<PageResponse> getAllPessoas(
            @Parameter(description = "Número da página") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Tamanho da página") @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // Corrigido o cálculo da página
        Page<PessoaModel> pessoasPage = pessoaService.getAllPessoas(pageable);

        if (pessoasPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        PageResponse pageResponse = new PageResponse(
                pessoasPage.getContent(),
                pessoasPage.getNumber(),
                pessoasPage.getTotalPages(),
                pessoasPage.getTotalElements());

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @Operation(
        summary = "Buscar pessoas por nome, data de nascimento e nome da mãe",
        description = "Retorna uma lista de pessoas com base nos parâmetros fornecidos.",
        tags = {"Pessoas", "get"})
    @ApiResponse(responseCode = "200", description = "Pessoas encontradas")
    @ApiResponse(responseCode = "204", description = "Nenhuma pessoa encontrada")
    @GetMapping("/buscar")
    public ResponseEntity<List<PessoaModel>> buscarPessoas(
            @Parameter(description = "Nome da pessoa") @RequestParam(required = false) String nome,
            @Parameter(description = "Data de nascimento da pessoa") @RequestParam(required = false) Date dataNascimento,
            @Parameter(description = "Nome da mãe da pessoa") @RequestParam(required = false) String nomeMae) {
        List<PessoaModel> pessoas = pessoaService.findByNomeContainingAndDataNascimentoAndNomeMaeContaining(nome,
                dataNascimento, nomeMae);
        if (pessoas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @Operation(
        summary = "Buscar pessoa pelo CPF",
        description = "Retorna uma pessoa com base no CPF fornecido.",
        tags = {"Pessoas", "get"})
    @ApiResponse(responseCode = "200", description = "Pessoa encontrada")
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaModel> getPessoaByCpf(@PathVariable("cpf") String cpf) {
        Optional<PessoaModel> pessoaOptional = pessoaService.getPessoaByCpf(cpf);
        return pessoaOptional.map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
        summary = "Inserir pessoas",
        description = "Insere uma lista de pessoas.",
        tags = {"Pessoas", "post"})
    @ApiResponse(responseCode = "201", description = "Pessoas inseridas com sucesso")
    @PostMapping("/inserir")
    public ResponseEntity<List<PessoaModel>> createPessoas(@RequestBody List<PessoaModel> pessoas) {
        List<PessoaModel> savedPessoas = pessoaService.saveOrUpdatePessoas(pessoas);
        return new ResponseEntity<>(savedPessoas, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Criar ou atualizar pessoa",
        description = "Cria ou atualiza uma pessoa.",
        tags = {"Pessoas", "post"})
    @ApiResponse(responseCode = "201", description = "Pessoa criada ou atualizada com sucesso")
    @PostMapping
    public ResponseEntity<PessoaModel> createOrUpdatePessoa(@RequestBody PessoaModel pessoa) {
        PessoaModel savedPessoa = pessoaService.saveOrUpdatePessoa(pessoa);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Atualizar pessoa",
        description = "Atualiza uma pessoa com base no ID fornecido.",
        tags = {"Pessoas", "put"})
    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaModel> updatePessoa(@Parameter(description = "ID da pessoa a ser atualizada") @PathVariable("id") int id, @RequestBody PessoaModel pessoa) {
        Optional<PessoaModel> existingPessoaOptional = pessoaService.getPessoaById(id);
        if (existingPessoaOptional.isPresent()) {
            PessoaModel existingPessoa = existingPessoaOptional.get();
            existingPessoa.setNome(pessoa.getNome()); // Atualize os outros campos conforme necessário
            PessoaModel updatedPessoa = pessoaService.saveOrUpdatePessoa(existingPessoa);
            return new ResponseEntity<>(updatedPessoa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletar uma pessoa pelo ID", description = "Deleta uma pessoa com base no ID fornecido.")
    @Tag(name = "Pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletePessoa(@Parameter(description = "ID da pessoa a ser deletada") @PathVariable("id") int id) {
        if (pessoaService.getPessoaById(id).isPresent()) {
            pessoaService.deletePessoa(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Buscar pessoas pelo nome", description = "Retorna uma lista de pessoas que correspondem ao nome fornecido.")
    @Tag(name = "Pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoas encontrada"),
            @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada")
    })
    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<List<PessoaModel>> findByNome(@Parameter(description = "Nome da pessoa a ser buscada") @PathVariable("nome") String nome) {
        List<PessoaModel> pessoas = pessoaService.findByNome(nome);
        if (!pessoas.isEmpty()) {
            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
