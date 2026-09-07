package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosCadastroUsuario;
import com.italo.funcionarios_api.model.Usuario;
import com.italo.funcionarios_api.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario cadastrarUsuario(DadosCadastroUsuario dados){

        var usuario = new Usuario(dados);

        usuarioRepository.save(usuario);

        return usuario;
    }

}
