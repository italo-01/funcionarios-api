package com.italo.funcionarios_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroFuncionario(

        @NotBlank
        String  nome,

        @NotBlank
        String cargo,

        @NotNull
        LocalDate dataNascimento,

        @NotNull
        BigDecimal salario

) {


}
