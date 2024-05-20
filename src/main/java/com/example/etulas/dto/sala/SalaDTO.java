package com.example.etulas.dto.sala;

import com.example.etulas.model.hospital.Hospital;

public record SalaDTO(
    String numeroDaSala,
    String uti, 
    String descricao, 
    Boolean ativo,
    Hospital hospital
    ) {
    
}
