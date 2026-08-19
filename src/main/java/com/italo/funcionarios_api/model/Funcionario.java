package com.italo.funcionarios_api.model;

import com.italo.funcionarios_api.dto.DadosCadastroFuncionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargo;
    private LocalDate dataNascimento;
    private boolean ativo;
    private BigDecimal salario;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.cargo = dados.cargo();
        this.dataNascimento = dados.dataNascimento();
        this.salario = dados.salario();
        this.ativo = true;
    }
}

