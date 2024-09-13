CREATE TABLE T_ETU_PACIENTE (
    id_paciente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nm_paciente VARCHAR(60) NOT NULL UNIQUE,
    tx_cpf CHAR(14) NOT NULL UNIQUE,
    tx_telefone CHAR(13) NOT NULL UNIQUE,
    nr_idade INT NOT NULL,
    ds_genero VARCHAR(9) CHECK (ds_genero IN ('MASCULINO', 'FEMININO'))
);


-- Inserindo o primeiro paciente
INSERT INTO T_ETU_PACIENTE (nm_paciente, tx_cpf, tx_telefone, nr_idade, ds_genero)
VALUES ('João Silva', '794.485.720-18', '11987654321', 30, 'MASCULINO');

-- Inserindo o segundo paciente
INSERT INTO T_ETU_PACIENTE (nm_paciente, tx_cpf, tx_telefone, nr_idade, ds_genero)
VALUES ('Maria Souza', '430.842.330-98', '21987654321', 25, 'FEMININO');

-- Inserindo o terceiro paciente
INSERT INTO T_ETU_PACIENTE (nm_paciente, tx_cpf, tx_telefone, nr_idade, ds_genero)
VALUES ('Pedro Lima', '950.268.200-93', '31987654321', 40, 'MASCULINO');
