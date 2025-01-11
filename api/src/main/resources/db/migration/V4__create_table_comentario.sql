-- Criação da tabela comentario
CREATE TABLE comentario (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    comentario VARCHAR(140) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    ocorrencia_id INTEGER NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (ocorrencia_id) REFERENCES ocorrencia(id)
);
