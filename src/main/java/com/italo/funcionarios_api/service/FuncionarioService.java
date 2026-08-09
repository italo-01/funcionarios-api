package com.italo.funcionarios_api.service;

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

    public void cadastrarFuncionario(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
    }
}
