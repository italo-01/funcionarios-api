package com.italo.funcionarios_api.controller;

import com.italo.funcionarios_api.dto.DadosAtualizarFuncionario;
import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.dto.DadosDetalhamentoFuncionario;
import com.italo.funcionarios_api.dto.DadosListagemFuncionario;
import com.italo.funcionarios_api.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@Tag(name = "Funcionario", description = "Gerenciamento de funcionarios.")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @Operation(summary = "Cadastra funcionario",description = "Cadastra um novo funcionario")
    public ResponseEntity<DadosDetalhamentoFuncionario> cadastrarFuncionario(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uribuilder) {

        var funcionario = funcionarioService.cadastrarFuncionario(dados);

        var uri = uribuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @GetMapping
    @Operation(summary = "Lista de todos os funcionarios ",description = "Visualiza todos os funcionarios cadastrado no sistema que estão ativos")
    public ResponseEntity<List<DadosListagemFuncionario>> listar(@RequestParam(required = false) Boolean ativos){

        var funcionarios = funcionarioService.listarFuncionarios(ativos);

        return ResponseEntity.ok(funcionarios);
    }

    @PutMapping
    @Operation(summary = "Atualiza funcionario",description = "Atualiza dados do funcionario existente")
    public ResponseEntity<DadosDetalhamentoFuncionario> atualizarFuncionario(@RequestBody @Valid DadosAtualizarFuncionario dados) {

        var funcionario = funcionarioService.atualizarFuncionario(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui funcionario",description = "Exclui funcionario do sitema definitivamente")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable long id ){

        funcionarioService.deletarFuncionario(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("desativar/{id}")
    @Operation(summary = "Desativa funcionario",description = "Desativa um funcionario do sitema")
    public ResponseEntity <Void> desativarfuncionario(@PathVariable long id){

        funcionarioService.desativarFuncionario(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("ativar/{id}")
    @Operation(summary = "Ativa funcionario",description = "Ativa um funcionario no sitema")
    public ResponseEntity <Void> ativarFuncionario(@PathVariable long id) {

        funcionarioService.ativarFuncionario(id);

        return ResponseEntity.noContent().build();
    }
}
