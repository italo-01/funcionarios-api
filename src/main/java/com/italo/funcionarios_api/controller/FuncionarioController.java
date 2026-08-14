package com.italo.funcionarios_api.controller;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.dto.DadosListagemFuncionario;
import com.italo.funcionarios_api.service.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")

public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrarFuncionario(@RequestBody @Valid DadosCadastroFuncionario dados) {
        funcionarioService.cadastrarFuncionario(dados);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<List<DadosListagemFuncionario>> listar(){
        var funcionarios = funcionarioService.listarFuncionarios();
        return ResponseEntity.ok(funcionarios);
        
    }
}
