package com.example.etulas.dto.fichaAtendimento;

import java.time.LocalDate;

public record FichaAtendimentoDTO(Double peso, String pressao, Double  altura, Double temperatura, String dores, LocalDate entradaPaciente, LocalDate saidaPaciente, Boolean ativo) {
    
}
