package com.italo.funcionarios_api.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginUsuario (
    @NotBlank
    String login,

    @NotBlank
    String Senha){
}
