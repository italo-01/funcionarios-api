package com.italo.funcionarios_api.repository;
import com.italo.funcionarios_api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
