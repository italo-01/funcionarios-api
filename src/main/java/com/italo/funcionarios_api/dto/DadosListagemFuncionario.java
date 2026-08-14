package com.italo.funcionarios_api.dto;

import com.italo.funcionarios_api.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosListagemFuncionario(
    Long id,
    String nome,
    String cargo,
    LocalDate dataNascimento,
    boolean ativo,
    BigDecimal salario
) {
    public DadosListagemFuncionario(Funcionario funcionario) {
        this(  funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getDataNascimento(),
                funcionario.isAtivo(),
                funcionario.getSalario()
        );
    }
}
