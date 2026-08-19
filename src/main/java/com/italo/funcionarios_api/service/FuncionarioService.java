package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosAtualizarFuncionario;
import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.dto.DadosListagemFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import com.italo.funcionarios_api.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public Funcionario cadastrarFuncionario(DadosCadastroFuncionario dados) {

        var funcionario = new Funcionario(dados);

        funcionarioRepository.save(funcionario);

        return funcionario;
    }
    @Transactional
    public List<DadosListagemFuncionario> listarFuncionarios(Boolean ativos) {
        var funcionarios = switch (ativos) {
            case null -> funcionarioRepository.findAll();
            case true -> funcionarioRepository.findByAtivoTrue();
            case false -> funcionarioRepository.findByAtivoFalse();
        };
        return funcionarios.stream().map(DadosListagemFuncionario::new).toList();
    }

    @Transactional
    public Funcionario atualizarFuncionario(@Valid DadosAtualizarFuncionario dados) {

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
        return funcionario;
    }

    @Transactional
    public void deletarFuncionario(Long id) {

        var funcionario = funcionarioRepository.findById(id);

        if( funcionario.isPresent()){
            funcionarioRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException();
        }

    }

    @Transactional
    public void desativarFuncionario(Long id) {

        var funcionario = funcionarioRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        funcionario.setAtivo(false);

    }
    @Transactional
    public void ativarFuncionario(Long id) {

        var funcionario = funcionarioRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        funcionario.setAtivo(true);
    }
}
