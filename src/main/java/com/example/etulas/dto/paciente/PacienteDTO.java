package com.example.etulas.dto.paciente;

import com.example.etulas.model.hospital.Hospital;

public record PacienteDTO(
    String nome, 
    String cpf, 
    String telefone, 
    int idade, 
    String genero,
    Hospital hospital
    ) {
    
}
