package com.example.projetosesp.controller;

import com.example.projetosesp.model.EnderecoModel;
import com.example.projetosesp.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/enderecos")
@Tag(name = "Enderecos", description = "Endpoints para gerenciar endereços")
public class EnderecoController {

    private final EnderecoService EnderecoService;

    @Autowired
    public EnderecoController(EnderecoService EnderecoService) {
        this.EnderecoService = EnderecoService;
    }

    @Operation(
        summary = "Obter todos os endereços",
        description = "Retorna uma lista de todos os endereços cadastrados.",
        tags = {"Endereços", "get"})
    @ApiResponse(responseCode = "200", description = "Lista de endereços encontrada")
    @GetMapping
    public ResponseEntity<List<EnderecoModel>> getAllEnderecos() {
        List<EnderecoModel> enderecos = EnderecoService.getAllEnderecos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @Operation(summary = "Obter endereço por ID", description = "Retorna um endereço com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> getEnderecoById(@Parameter(description = "ID do endereço a ser obtido") @PathVariable int id) {
        EnderecoModel endereco = EnderecoService.getEnderecoById(id);
        if (endereco != null) {
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
        summary = "Criar endereço",
        description = "Cria um novo endereço.",
        tags = {"Enderecos", "post"})
    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso")
    @PostMapping("/inserir")
    public ResponseEntity<EnderecoModel> createEndereco(@jakarta.validation.Valid @RequestBody EnderecoModel enderecoModel) {
        EnderecoModel createdEndereco = EnderecoService.createEndereco(enderecoModel);
        return new ResponseEntity<>(createdEndereco, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Atualizar endereço",
        description = "Atualiza um endereço existente com base no ID fornecido.",
        tags = {"Enderecos", "put"})
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoModel> updateEndereco(@PathVariable int id, @jakarta.validation.Valid @RequestBody EnderecoModel enderecoModel) {
        EnderecoModel updatedEndereco = EnderecoService.updateEndereco(id, enderecoModel);
        if (updatedEndereco != null) {
            return new ResponseEntity<>(updatedEndereco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
        summary = "Deletar endereço",
        description = "Remove um endereço existente com base no ID fornecido.",
        tags = {"Enderecos", "delete"})
    @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable int id) {
        EnderecoService.deleteEndereco(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
