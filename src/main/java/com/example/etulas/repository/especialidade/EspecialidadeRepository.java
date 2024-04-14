package com.example.etulas.repository.especialidade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.especialidades.Especialidades;

public interface EspecialidadeRepository extends JpaRepository<Especialidades, Long>{
    
}
