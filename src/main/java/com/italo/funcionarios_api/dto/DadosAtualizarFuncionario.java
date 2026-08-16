package com.italo.funcionarios_api.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizarFuncionario(
        @NotNull
        Long id,

        String nome,

        String cargo,

        BigDecimal salario
) {
}
