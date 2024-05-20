package com.example.etulas.dto.endereco;

import com.example.etulas.model.endereco.EnderecoEnum;
import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.model.paciente.Paciente;

public record EnderecoDTO(
    String numeroEdificio, 
    String logadouro, 
    String bairro, 
    String cidade, 
    EnderecoEnum enderecoEnum,
    Hospital hospital,
    Paciente paciente
    ) {
    
}
