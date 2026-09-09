package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosCadastroUsuario;
import com.italo.funcionarios_api.model.Usuario;
import com.italo.funcionarios_api.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario cadastarUsuario(DadosCadastroUsuario dados){
        var user = new Usuario(dados);
        usuarioRepository.save(user);
        return user;
    }
}
