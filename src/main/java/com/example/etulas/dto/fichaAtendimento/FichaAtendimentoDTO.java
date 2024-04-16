package com.example.etulas.dto.fichaAtendimento;

import java.time.LocalDate;

public record FichaAtendimentoDTO(float peso, String pressao, float  altura, float temperatura, String dores, LocalDate entradaPaciente, LocalDate saidaPaciente, Boolean ativo) {
    
}
