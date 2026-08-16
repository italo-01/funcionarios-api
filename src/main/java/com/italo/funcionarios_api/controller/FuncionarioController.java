package com.italo.funcionarios_api.controller;

import com.italo.funcionarios_api.dto.DadosAtualizarFuncionario;
import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.dto.DadosDetalhamentoFuncionario;
import com.italo.funcionarios_api.dto.DadosListagemFuncionario;
import com.italo.funcionarios_api.repository.FuncionarioRepository;
import com.italo.funcionarios_api.service.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/funcionario")

public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoFuncionario> cadastrarFuncionario(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uribuilder) {

        var funcionario = funcionarioService.cadastrarFuncionario(dados);

        var uri = uribuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemFuncionario>> listar(){

        var funcionarios = funcionarioService.listarFuncionarios();

        return ResponseEntity.ok(funcionarios);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoFuncionario> atualizarFuncionario(@RequestBody @Valid DadosAtualizarFuncionario dados) {

        var funcionario = funcionarioService.atualizarFuncionario(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));

    }
}
