-- Criação da tabela ocorrencia
CREATE TABLE ocorrencia (
    id SERIAL PRIMARY KEY,
    resumo VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    endereco VARCHAR(100),
    latitude NUMERIC,
    longitude NUMERIC,
    tipo_ocorrencia_id INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    FOREIGN KEY (tipo_ocorrencia_id) REFERENCES tipo_ocorrencia(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
