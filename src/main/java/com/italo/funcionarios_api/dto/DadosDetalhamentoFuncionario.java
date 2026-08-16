package com.italo.funcionarios_api.dto;

import com.italo.funcionarios_api.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoFuncionario(
        Long id,
        String nome,
        String cargo,
        LocalDate dataNascimento,
        Boolean ativo,
        BigDecimal salario

){
    public DadosDetalhamentoFuncionario(Funcionario funcionario) {
        this(  funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getDataNascimento(),
                funcionario.isAtivo(),
                funcionario.getSalario()
        );
    }
}

