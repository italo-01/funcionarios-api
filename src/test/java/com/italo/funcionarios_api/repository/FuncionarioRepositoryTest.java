package com.italo.funcionarios_api.repository;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import com.italo.funcionarios_api.model.Funcionario;
import jakarta.persistence.EntityManager;
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
    void findByAtivoFalse() {
    }

    private Funcionario createFuncionario(DadosCadastroFuncionario dados) {
        Funcionario funcionario = new Funcionario(dados);
        this.entityManager.persist(funcionario);
        return funcionario;
    }
}