CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Criação da tabela tipo_ocorrencia
CREATE TABLE tipo_ocorrencia (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);