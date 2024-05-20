package com.example.etulas.dto.convenio;

import com.example.etulas.model.paciente.Paciente;

public record ConvenioDTO(
    Long Id, 
    String nome,
     String cnpj, 
     String telefone, 
     Boolean ativo,
     Paciente paciente
     
     ) {

}
