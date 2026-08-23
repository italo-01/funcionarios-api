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
            case Boolean b when b  -> funcionarioRepository.findByAtivoTrue();
            case Boolean b -> funcionarioRepository.findByAtivoFalse();
        };
        return funcionarios.stream().map(DadosListagemFuncionario::new).toList();
    }

    @Transactional
    public Funcionario atualizarFuncionario(@Valid DadosAtualizarFuncionario dados) {

        var funcionario = funcionarioRepository.findById(dados.id())
                .orElseThrow(EntityNotFoundException::new);

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

        funcionarioRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        funcionarioRepository.deleteById(id);
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
