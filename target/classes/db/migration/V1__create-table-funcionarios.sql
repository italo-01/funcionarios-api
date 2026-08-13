CREATE TABLE funcionarios (
      id BIGSERIAL PRIMARY KEY,
      nome VARCHAR(100) NOT NULL,
      cargo VARCHAR(100) NOT NULL,
      data_nascimento DATE NOT NULL,
      ativo BOOLEAN NOT NULL,
      salario NUMERIC(10, 2) NOT NULL
);