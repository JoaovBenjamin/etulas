CREATE TABLE T_ETU_CONVENIO (
    id_convenio BIGINT AUTO_INCREMENT PRIMARY KEY,
    nm_nome VARCHAR(60) NOT NULL,
    tx_cnpj VARCHAR(20) NOT NULL UNIQUE,
    tx_telefone VARCHAR(13) NOT NULL,
    st_ativo BOOLEAN,
    paciente_id BIGINT NOT NULL,
    CONSTRAINT fk_paciente_convenio FOREIGN KEY (paciente_id) REFERENCES T_ETU_PACIENTE(id_paciente)
);


-- Inserindo o primeiro registro de convenio
INSERT INTO T_ETU_CONVENIO (nm_nome, tx_cnpj, tx_telefone, st_ativo, paciente_id)
VALUES ('Unimed Sul de Minas', '84.511.594/0001-95', '35991234567', TRUE, 1);

-- Inserindo o segundo registro de convenio
INSERT INTO T_ETU_CONVENIO (nm_nome, tx_cnpj, tx_telefone, st_ativo, paciente_id)
VALUES ('Amil Assistência Médica', '14.272.897/0001-17', '11987654321', TRUE, 2);

-- Inserindo o terceiro registro de convenio
INSERT INTO T_ETU_CONVENIO (nm_nome, tx_cnpj, tx_telefone, st_ativo, paciente_id)
VALUES ('Bradesco Saúde', '29.285.523/0001-03', '21987654322', FALSE, 3);
