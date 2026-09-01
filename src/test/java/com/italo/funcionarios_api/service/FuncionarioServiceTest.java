package com.italo.funcionarios_api.service;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.dto.DadosListagemFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import com.italo.funcionarios_api.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;


    @Test
    @DisplayName("should register a new  employee successfully")
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
    @DisplayName("should call findAll when ativos parameter is null")
    void listarFuncionariosFindAll() {
        Funcionario funcionario1 = new Funcionario(1L, "Italo", "Dev",
                LocalDate.of(1999, 10, 10), true, new BigDecimal("1000.00"));
        Funcionario funcionario2 = new Funcionario(2L, "Maria", "QA",
                LocalDate.of(1995, 5, 5), false, new BigDecimal("1200.00"));

        when(funcionarioRepository.findAll()).thenReturn(List.of(funcionario1, funcionario2));

        List<DadosListagemFuncionario> resultado = funcionarioService.listarFuncionarios(null);

        assertThat(resultado).hasSize(2);
        verify(funcionarioRepository).findAll();
        verify(funcionarioRepository, never()).findByAtivoTrue();
        verify(funcionarioRepository, never()).findByAtivoFalse();
    }

    @Test
    @DisplayName("should call findByAtivoFalse when ativos parameter is false")
    void listarFuncionariosFindByAtivoFalse() {
        Funcionario funcionario1 = new Funcionario(1L, "Italo", "Dev",
                LocalDate.of(1999, 10, 10), false, new BigDecimal("1000.00"));
        Funcionario funcionario2 = new Funcionario(2L, "Maria", "QA",
                LocalDate.of(1995, 5, 5), false, new BigDecimal("1200.00"));

        when(funcionarioRepository.findByAtivoFalse()).thenReturn(List.of(funcionario1, funcionario2));

        List<DadosListagemFuncionario> resultado = funcionarioService.listarFuncionarios(false);

        assertThat(resultado).hasSize(2);
        verify(funcionarioRepository).findByAtivoFalse();
        verify(funcionarioRepository, never()).findAll();
        verify(funcionarioRepository, never()).findByAtivoTrue();
    }

    @Test
    @DisplayName("should call findByAtivoTrue when ativos parameter is true")
    void listarFuncionariosFindByAtivoTrue() {
        Funcionario funcionario1 = new Funcionario(1L, "Italo", "Dev",
                LocalDate.of(1999, 10, 10), true, new BigDecimal("1000.00"));
        Funcionario funcionario2 = new Funcionario(2L, "Maria", "QA",
                LocalDate.of(1995, 5, 5), true, new BigDecimal("1200.00"));

        when(funcionarioRepository.findByAtivoTrue()).thenReturn(List.of(funcionario1, funcionario2));

        List<DadosListagemFuncionario> resultado = funcionarioService.listarFuncionarios(true);

        assertThat(resultado).hasSize(2);
        verify(funcionarioRepository, never()).findAll();
        verify(funcionarioRepository).findByAtivoTrue();
        verify(funcionarioRepository, never()).findByAtivoFalse();
    }

    @Test
    @DisplayName("should delete funcionario successfully when id exists")
    void deletarFuncionariocase1() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

        funcionarioService.deletarFuncionario(id);

        verify(funcionarioRepository).deleteById(id);

    }

    @Test
    void deletarFuncionariocase2(){
        Long id = 99L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> funcionarioService.deletarFuncionario(id))
                .isInstanceOf(EntityNotFoundException.class);

        verify(funcionarioRepository, never()).deleteById(any());

    }

    @Test
    @DisplayName("should deactivate funcionario successfully when id exists")
    void desativarFuncionario1() {
    Long id = 1L;
    Funcionario funcionario = new Funcionario();
    funcionario.setAtivo(true);

    when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

    funcionarioService.desativarFuncionario(id);

    assertThat(funcionario.isAtivo()).isFalse();
}

    @Test
    @DisplayName("should deactivate funcionario successfully when id exists")
    void desativarFuncionariocase2() {

        Long id = 99L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> funcionarioService.desativarFuncionario(id))
            .isInstanceOf(EntityNotFoundException.class);

    }
    @Test
    @DisplayName("should activate funcionario successfully when id exists")
    void ativarFuncionariocase1(){
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setAtivo(false);

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

        funcionarioService.ativarFuncionario(id);

        assertThat(funcionario.isAtivo()).isTrue();
    }
    @Test
    @DisplayName("should throw exception when trying to activate a non-existing funcionario")
    void ativarFuncionariocase2(){
        Long id = 99L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> funcionarioService.desativarFuncionario(id))
                .isInstanceOf(EntityNotFoundException.class);

    }
}