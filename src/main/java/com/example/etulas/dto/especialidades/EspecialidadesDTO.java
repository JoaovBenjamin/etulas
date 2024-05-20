package com.example.etulas.dto.especialidades;

import com.example.etulas.model.especialidades.EspecialidadesEnum;
import com.example.etulas.model.hospital.Hospital;

public record EspecialidadesDTO(
    EspecialidadesEnum nomeEspecialidade, 
    String descricaoEspecialidade, 
    String descricaoProcedimento, 
    Boolean ativo, 
    Hospital hospital ) {
    
}
