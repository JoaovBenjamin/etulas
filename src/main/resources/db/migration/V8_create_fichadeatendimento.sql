CREATE TABLE T_ETU_FICHA_ATENDIMENTO (
    id_atendimento BIGINT AUTO_INCREMENT PRIMARY KEY,
    nr_peso DOUBLE NOT NULL,
    nr_pressao VARCHAR(255) NOT NULL,
    nr_altura DOUBLE NOT NULL,
    nr_temperatura DOUBLE NOT NULL,
    ds_dores VARCHAR(200) NOT NULL,
    dt_entrada DATE NOT NULL,
    dt_encerramento DATE,
    st_ativo BOOLEAN,
    especialidades_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    CONSTRAINT fk_especialidades_ficha FOREIGN KEY (especialidades_id) REFERENCES T_ETU_ESPECIALIDADES(id),
    CONSTRAINT fk_paciente_ficha FOREIGN KEY (paciente_id) REFERENCES T_ETU_PACIENTE(id)
);

-- Inserindo o primeiro atendimento
INSERT INTO T_ETU_FICHA_ATENDIMENTO (nr_peso, nr_pressao, nr_altura, nr_temperatura, ds_dores, dt_entrada, dt_encerramento, st_ativo, especialidades_id, paciente_id)
VALUES (70.5, '120/80', 1.75, 36.5, 'Dores na cabeça e tontura constante', '2024-09-10', '2024-09-12', TRUE, 1, 1);

-- Inserindo o segundo atendimento
INSERT INTO T_ETU_FICHA_ATENDIMENTO (nr_peso, nr_pressao, nr_altura, nr_temperatura, ds_dores, dt_entrada, dt_encerramento, st_ativo, especialidades_id, paciente_id)
VALUES (65.0, '110/70', 1.68, 37.0, 'Dores no peito e falta de ar', '2024-09-08', '2024-09-09', TRUE, 2, 2);

-- Inserindo o terceiro atendimento
INSERT INTO T_ETU_FICHA_ATENDIMENTO (nr_peso, nr_pressao, nr_altura, nr_temperatura, ds_dores, dt_entrada, dt_encerramento, st_ativo, especialidades_id, paciente_id)
VALUES (80.2, '130/85', 1.80, 36.8, 'Dor abdominal e febre alta', '2024-09-05', '2024-09-06', FALSE, 3, 3);
