package com.example.etulas.dto.anamnesia;

import com.example.etulas.model.paciente.Paciente;

public record AnamnesiaDTO(
    String lesoes, 
    String genetica, 
    String cronicas, 
    String alergias,
    Paciente paciente
    ) {
    
}
