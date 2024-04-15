package com.example.etulas.dto.endereco;

import com.example.etulas.model.endereco.EnderecoEnum;

public record EnderecoDTO(String numeroEdificio, String logadouro, String bairro, String cidade, EnderecoEnum enderecoEnum) {
    
}
