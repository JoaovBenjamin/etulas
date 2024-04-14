package com.example.etulas.repository.endereco;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
