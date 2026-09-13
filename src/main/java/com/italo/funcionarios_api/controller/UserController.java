package com.italo.funcionarios_api.controller;

import com.italo.funcionarios_api.dto.DadosCadastroUsuario;
import com.italo.funcionarios_api.model.Usuario;
import com.italo.funcionarios_api.security.SecuriyConfig;
import com.italo.funcionarios_api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UsuarioService usuarioService;

    public UserController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    public ResponseEntity<Void> cadastrarUsuario(DadosCadastroUsuario dados){

        var user = usuarioService.cadastarUsuario(dados);
        return ResponseEntity.status(201).build();

    }
}
