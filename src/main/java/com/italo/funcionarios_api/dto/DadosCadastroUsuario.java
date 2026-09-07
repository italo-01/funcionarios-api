package com.italo.funcionarios_api.dto;

import jakarta.validation.constraints.NotBlank;

public class DadosCadastroUsuario {
    @NotBlank
    String login;

    @NotBlank
    String senha;
}
