CREATE TABLE T_ETU_ESPECIALIDADES (
    id_especialidade BIGINT AUTO_INCREMENT PRIMARY KEY,
    nm_especialidade ENUM('CARDIOLOGIA', 'ORTOPEDIA', 'PEDIATRIA', 'NEUROLOGIA') NOT NULL,  -- Exemplo de valores para EspecialidadesEnum
    ds_especialidade VARCHAR(200) NOT NULL,
    ds_procedimento VARCHAR(200) NOT NULL,
    st_ativo BOOLEAN,
    hospital_id BIGINT NOT NULL,
    CONSTRAINT fk_hospital_especialidade FOREIGN KEY (hospital_id) REFERENCES T_ETU_HOSPITAL(id)
);

-- Inserindo a primeira especialidade
INSERT INTO T_ETU_ESPECIALIDADES (nm_especialidade, ds_especialidade, ds_procedimento, st_ativo, hospital_id)
VALUES ('CARDIOLOGIA', 'Especialidade que trata das doenças do coração e do sistema circulatório.', 
        'Procedimentos como ecocardiograma e cateterismo.', TRUE, 1);

-- Inserindo a segunda especialidade
INSERT INTO T_ETU_ESPECIALIDADES (nm_especialidade, ds_especialidade, ds_procedimento, st_ativo, hospital_id)
VALUES ('ORTOPEDIA', 'Especialidade que cuida de problemas nos ossos, músculos e articulações.',
        'Procedimentos como radiografias e cirurgias ortopédicas.', TRUE, 2);

-- Inserindo a terceira especialidade
INSERT INTO T_ETU_ESPECIALIDADES (nm_especialidade, ds_especialidade, ds_procedimento, st_ativo, hospital_id)
VALUES ('NEUROLOGIA', 'Especialidade responsável pelo diagnóstico e tratamento de doenças do sistema nervoso.',
        'Procedimentos como ressonância magnética e eletroencefalograma.', FALSE, 3);
