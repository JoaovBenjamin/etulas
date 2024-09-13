CREATE TABLE T_ETU_ENDERECO (
    id_endereco BIGINT AUTO_INCREMENT PRIMARY KEY,
    nr_edificio VARCHAR(10) NOT NULL,
    nm_logadouro VARCHAR(60) NOT NULL,
    nm_bairro VARCHAR(60) NOT NULL,
    nm_cidade VARCHAR(60) NOT NULL,
    nm_uf ENUM('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO') NOT NULL,
    hospital_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    CONSTRAINT fk_hospital_endereco FOREIGN KEY (hospital_id) REFERENCES T_ETU_HOSPITAL(id),
    CONSTRAINT fk_paciente_endereco FOREIGN KEY (paciente_id) REFERENCES T_ETU_PACIENTE(id_paciente)
);


-- Inserindo o primeiro registro de endereco
INSERT INTO T_ETU_ENDERECO (nr_edificio, nm_logadouro, nm_bairro, nm_cidade, nm_uf, hospital_id, paciente_id)
VALUES ('101', 'Rua das Flores', 'Centro', 'São Paulo', 'SP', 1, 1);

-- Inserindo o segundo registro de endereco
INSERT INTO T_ETU_ENDERECO (nr_edificio, nm_logadouro, nm_bairro, nm_cidade, nm_uf, hospital_id, paciente_id)
VALUES ('202', 'Avenida Paulista', 'Bela Vista', 'São Paulo', 'SP', 2, 2);

-- Inserindo o terceiro registro de endereco
INSERT INTO T_ETU_ENDERECO (nr_edificio, nm_logadouro, nm_bairro, nm_cidade, nm_uf, hospital_id, paciente_id)
VALUES ('303', 'Rua XV de Novembro', 'Centro', 'Curitiba', 'PR', 3, 3);
