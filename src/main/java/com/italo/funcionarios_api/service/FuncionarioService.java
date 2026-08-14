package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.dto.DadosListagemFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import com.italo.funcionarios_api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void cadastrarFuncionario(DadosCadastroFuncionario dados) {
        var funcionario = new Funcionario(dados);
        funcionarioRepository.save(funcionario);
    }

    public java.util.List<DadosListagemFuncionario> listarFuncionarios() {
        var funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(DadosListagemFuncionario::new).toList();
    }
}
