package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosAtualizarFuncionario;
import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.dto.DadosListagemFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import com.italo.funcionarios_api.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<DadosListagemFuncionario> listarFuncionarios() {
        var funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(DadosListagemFuncionario::new).toList();
    }

    public void atualizarFuncionario(@Valid DadosAtualizarFuncionario dados) {

        var funcionario = funcionarioRepository.getReferenceById(dados.id());

        if (dados.nome() != null) {
            funcionario.setNome(dados.nome());
        }

        if (dados.cargo() != null) {
            funcionario.setCargo(dados.cargo());
        }

        if (dados.salario() != null) {
            funcionario.setSalario(dados.salario());
        }

    }

}
