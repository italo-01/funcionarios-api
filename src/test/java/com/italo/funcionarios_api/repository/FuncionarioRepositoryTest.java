package com.italo.funcionarios_api.repository;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class FuncionarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void findByAtivoTrue() {

    }

    @Test
    @DisplayName("should get User sucessfuly from Db")
    void findByAtivoFalseCase1() {
        DadosCadastroFuncionario data = new DadosCadastroFuncionario(
                "Italo",
                "Dev",
                java.time.LocalDate.of(1999, 10, 10),
                new java.math.BigDecimal("1000.00")
        );
        this.createFuncionario(data);
    }

        private Funcionario createFuncionario(DadosCadastroFuncionario dados) {
            Funcionario funcionario = new Funcionario(dados);
            this.entityManager.persist(funcionario);
            return funcionario;
    }
}