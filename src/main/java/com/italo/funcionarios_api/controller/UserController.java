package com.italo.funcionarios_api.controller;

import com.italo.funcionarios_api.dto.DadosCadastroUsuario;
import com.italo.funcionarios_api.dto.DadosLoginUsuario;
import com.italo.funcionarios_api.dto.DadosTokenJwt;
import com.italo.funcionarios_api.model.Usuario;
import com.italo.funcionarios_api.service.TokenService;
import com.italo.funcionarios_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    public UserController (AuthenticationManager authenticationManager, UsuarioService usuarioService, TokenService tokenService){
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados){

        usuarioService.cadastrarUsuario(dados);
        return ResponseEntity.status(201).body(Map.of("Mensagem", "cadastrado com sucesso"));

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginFuncioanrio (@RequestBody @Valid DadosLoginUsuario dados){
         var login = new UsernamePasswordAuthenticationToken(dados.login(), dados.Senha());

         var autenticacao = authenticationManager.authenticate(login);

        var tokemJWT = tokenService.gerarToken((Usuario) Objects.requireNonNull(autenticacao.getPrincipal()));

         return ResponseEntity.ok(new DadosTokenJwt(tokemJWT));
    }
}
