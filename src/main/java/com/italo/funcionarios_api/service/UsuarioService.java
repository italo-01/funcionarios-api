package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosCadastroUsuario;
import com.italo.funcionarios_api.model.Usuario;
import com.italo.funcionarios_api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrarUsuario(DadosCadastroUsuario dados){

        var hash = passwordEncoder.encode(dados.Senha());

        var user = new Usuario();
        user.setLogin(dados.nome());
        user.setSenha(hash);

        usuarioRepository.save(user);

        return user;
    }
}
