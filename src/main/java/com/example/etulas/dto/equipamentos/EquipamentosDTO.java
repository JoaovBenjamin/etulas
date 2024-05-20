package com.example.etulas.dto.equipamentos;

import com.example.etulas.model.hospital.Hospital;

public record EquipamentosDTO(
    String nome, 
    String procedimento, 
    int sala, 
    Boolean ativo,
    Hospital hospital
    ) {
    
}
