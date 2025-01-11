-- Criação da tabela usuario
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    login VARCHAR(20) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);
