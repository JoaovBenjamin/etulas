CREATE TABLE T_ETU_ANAMNESIA (
    id_anamnesia BIGINT AUTO_INCREMENT PRIMARY KEY,
    ds_lesoes VARCHAR(200) NOT NULL,
    ds_doencas_geneticas VARCHAR(200) NOT NULL,
    ds_doencas_cronicas VARCHAR(200) NOT NULL,
    ds_alergias VARCHAR(200) NOT NULL,
    paciente_id BIGINT NOT NULL,
    CONSTRAINT fk_paciente FOREIGN KEY (paciente_id) REFERENCES T_ETU_PACIENTE(id_paciente)
);


-- Inserindo o primeiro registro de anamnesia
INSERT INTO T_ETU_ANAMNESIA (ds_lesoes, ds_doencas_geneticas, ds_doencas_cronicas, ds_alergias, paciente_id)
VALUES ('Lesão no joelho', 'História de diabetes', 'Hipertensão', 'Alergia a amendoim', 1);

-- Inserindo o segundo registro de anamnesia
INSERT INTO T_ETU_ANAMNESIA (ds_lesoes, ds_doencas_geneticas, ds_doencas_cronicas, ds_alergias, paciente_id)
VALUES ('Fratura no braço', 'Nenhuma', 'Asma', 'Alergia a poeira', 2);

-- Inserindo o terceiro registro de anamnesia
INSERT INTO T_ETU_ANAMNESIA (ds_lesoes, ds_doencas_geneticas, ds_doencas_cronicas, ds_alergias, paciente_id)
VALUES ('Entorse no tornozelo', 'Doença de Alzheimer', 'Diabetes tipo 2', 'Nenhuma', 3);
