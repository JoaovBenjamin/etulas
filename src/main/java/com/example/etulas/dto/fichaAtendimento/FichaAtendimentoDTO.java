package com.example.etulas.dto.fichaAtendimento;

import java.time.LocalDate;

import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.model.paciente.Paciente;

public record FichaAtendimentoDTO(
    Double peso, 
    String pressao, 
    Double  altura, 
    Double temperatura, 
    String dores, 
    LocalDate entradaPaciente, 
    LocalDate saidaPaciente, 
    Boolean ativo, 
    Hospital hospital, 
    Especialidades especialidades, 
    Paciente paciente) {
    
}
