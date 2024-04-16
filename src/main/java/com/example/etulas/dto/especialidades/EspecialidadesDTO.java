package com.example.etulas.dto.especialidades;

import com.example.etulas.model.especialidades.EspecialidadesEnum;

public record EspecialidadesDTO(EspecialidadesEnum nomeEspecialidade, String descricaoEspecialidade, String descricaoProcedimento, Boolean ativo ) {
    
}
