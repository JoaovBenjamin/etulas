package com.example.etulas.repository.especialidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.especialidades.Especialidades;

public interface EspecialidadeRepository extends JpaRepository<Especialidades, Long>{
      
    
    Page<Especialidades> findByNome(String especialidade, Pageable pageable);
    
}
