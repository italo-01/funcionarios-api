package com.italo.funcionarios_api.repository;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class FuncionarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Test
    @DisplayName("should return only active funcionarios when findByAtivoTrue is called")
    void findByAtivoTrue() {
        this.criarFuncionarioAtivoEInativo();

        List<Funcionario> ativos = this.funcionarioRepository.findByAtivoTrue();

        assertThat(ativos)
                .hasSize(1)
                .extracting(Funcionario::getNome)
                .containsExactly("Italo");
    }

    @Test
    @DisplayName("should return only inactive funcionarios when findByAtivoFalse is called")
    void findByAtivoFalseCase() {
        this.criarFuncionarioAtivoEInativo();

        List<Funcionario> inativos = this.funcionarioRepository.findByAtivoFalse();

        assertThat(inativos)
                .hasSize(1)
                .extracting(Funcionario::getNome)
                .containsExactly("Maria");
    }

    /**
     * Cria um cenário padrão com dois funcionários:
     * - Italo: permanece ativo (padrão do construtor)
     * - Maria: criada e depois desativada manualmente
     * Sincroniza as mudanças com o banco (flush) antes de retornar.
     */
    private void criarFuncionarioAtivoEInativo() {
        DadosCadastroFuncionario dadosAtivo = new DadosCadastroFuncionario(
                "Italo",
                "Dev",
                LocalDate.of(1999, 10, 10),
                new BigDecimal("1000.00")
        );
        this.createFuncionario(dadosAtivo);

        DadosCadastroFuncionario dadosInativo = new DadosCadastroFuncionario(
                "Maria",
                "QA",
                LocalDate.of(1995, 5, 5),
                new BigDecimal("1200.00")
        );
        Funcionario inativo = this.createFuncionario(dadosInativo);
        inativo.setAtivo(false);

        this.entityManager.flush();
    }

    private Funcionario createFuncionario(DadosCadastroFuncionario dados) {
        Funcionario funcionario = new Funcionario(dados);
        this.entityManager.persist(funcionario);
        return funcionario;
    }
}