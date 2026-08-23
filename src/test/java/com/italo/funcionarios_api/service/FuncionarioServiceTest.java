package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import com.italo.funcionarios_api.repository.FuncionarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;


    @Test
    @DisplayName("should register a new semployee successfully")
    void cadastrarFuncionario() {

        DadosCadastroFuncionario funcionario = new DadosCadastroFuncionario(
                "Italo", "Montador",  LocalDate.of(1995, 5, 15),
                       new BigDecimal("3000.00")
        );

        Funcionario funcionarioSalvo = funcionarioService.cadastrarFuncionario(funcionario);

        assertThat(funcionarioSalvo.getNome()).isEqualTo("Italo");
        assertThat(funcionarioSalvo.getCargo()).isEqualTo("Montador");
        assertThat(funcionarioSalvo.getDataNascimento()).isEqualTo(LocalDate.of(1995, 5, 15));
        assertThat(funcionarioSalvo.getSalario()).isEqualByComparingTo(new BigDecimal("3000.00"));
        assertThat(funcionarioSalvo.isAtivo()).isTrue();

        verify(funcionarioRepository).save(any(Funcionario.class));

    }

    @Test
    void listarFuncionarios() {
    }

    @Test
    void atualizarFuncionario() {
    }

    @Test
    void deletarFuncionario() {
    }

    @Test
    void desativarFuncionario() {
    }

    @Test
    void ativarFuncionario() {
    }
}