package com.example.etulas.repository.convenio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.convenio.Convenio;

public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
    
}
