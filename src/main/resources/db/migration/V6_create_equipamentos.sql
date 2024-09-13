CREATE TABLE T_ETU_EQUIPAMENTOS_MEDICOS (
    id_equipamentos BIGINT AUTO_INCREMENT PRIMARY KEY,
    nm_equipamento VARCHAR(60) NOT NULL,
    ds_procedimento VARCHAR(200) NOT NULL,
    nr_sala INT NOT NULL,
    st_ativo BOOLEAN,
    hospital_id BIGINT NOT NULL,
    CONSTRAINT fk_hospital_equipamentos FOREIGN KEY (hospital_id) REFERENCES T_ETU_HOSPITAL(id)
);

-- Inserindo o primeiro equipamento médico
INSERT INTO T_ETU_EQUIPAMENTOS_MEDICOS (nm_equipamento, ds_procedimento, nr_sala, st_ativo, hospital_id)
VALUES ('Monitor Cardíaco', 'Monitora sinais vitais durante procedimentos médicos', 101, TRUE, 1);

-- Inserindo o segundo equipamento médico
INSERT INTO T_ETU_EQUIPAMENTOS_MEDICOS (nm_equipamento, ds_procedimento, nr_sala, st_ativo, hospital_id)
VALUES ('Máquina de Hemodiálise', 'Realiza a filtração do sangue de pacientes com insuficiência renal', 202, TRUE, 2);

-- Inserindo o terceiro equipamento médico
INSERT INTO T_ETU_EQUIPAMENTOS_MEDICOS (nm_equipamento, ds_procedimento, nr_sala, st_ativo, hospital_id)
VALUES ('Ventilador Mecânico', 'Auxilia na respiração de pacientes com insuficiência respiratória', 303, TRUE, 3);
