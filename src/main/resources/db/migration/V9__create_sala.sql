CREATE TABLE T_ETU_SALAS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tx_salas VARCHAR(255) NOT NULL,  -- Número da sala (não pode ser nulo)
    qtd_leitos_uti VARCHAR(255) NOT NULL,  -- Quantidade de leitos UTI (não pode ser nulo)
    ds_salas VARCHAR(200) NOT NULL,  -- Descrição da sala (mínimo de 20 e máximo de 200 caracteres)
    st_ativo BOOLEAN,  -- Indicador de status ativo/inativo
    hospital_id BIGINT NOT NULL,  -- Chave estrangeira para a tabela hospital (não pode ser nulo)
    CONSTRAINT fk_hospital_sala FOREIGN KEY (hospital_id) REFERENCES T_ETU_HOSPITAL(id)
);

-- Inserindo a primeira sala
INSERT INTO T_ETU_SALAS (tx_salas, qtd_leitos_uti, ds_salas, st_ativo, hospital_id)
VALUES ('Sala 101', '10 leitos', 'Sala equipada com monitoramento completo para cuidados intensivos.', TRUE, 1);

-- Inserindo a segunda sala
INSERT INTO T_ETU_SALAS (tx_salas, qtd_leitos_uti, ds_salas, st_ativo, hospital_id)
VALUES ('Sala 202', '8 leitos', 'Sala de UTI com foco em cuidados neurológicos.', TRUE, 2);

-- Inserindo a terceira sala
INSERT INTO T_ETU_SALAS (tx_salas, qtd_leitos_uti, ds_salas, st_ativo, hospital_id)
VALUES ('Sala 303', '12 leitos', 'Sala de UTI pediátrica equipada com suporte avançado de vida.', FALSE, 3);
