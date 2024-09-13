CREATE TABLE T_ETU_HOSPITAL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nm_nome VARCHAR(60) NOT NULL,
    tx_telefone CHAR(11) NOT NULL,
    tx_cnpj CHAR(18) NOT NULL UNIQUE,
    st_ativo BOOLEAN,
    hospital_id BIGINT,
    CONSTRAINT fk_hospital FOREIGN KEY (hospital_id) REFERENCES T_ETU_HOSPITAL(id)
);

-- Inserindo o primeiro hospital
INSERT INTO T_ETU_HOSPITAL (nm_nome, tx_telefone, tx_cnpj, st_ativo, hospital_id)
VALUES ('Hospital São João', '11987654321', '93.521.909/0001-76', TRUE, NULL);

-- Inserindo o segundo hospital
INSERT INTO T_ETU_HOSPITAL (nm_nome, tx_telefone, tx_cnpj, st_ativo, hospital_id)
VALUES ('Hospital Santa Casa', '21987654321', '16.876.942/0001-78', TRUE, NULL);

-- Inserindo o terceiro hospital (filial de outro hospital)
INSERT INTO T_ETU_HOSPITAL (nm_nome, tx_telefone, tx_cnpj, st_ativo, hospital_id)
VALUES ('Hospital São João - Filial', '31987654321', '15.081.743/0001-00', TRUE, 1);
