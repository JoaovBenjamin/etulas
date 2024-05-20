package com.example.etulas.repository.especialidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.etulas.model.especialidades.Especialidades;

public interface EspecialidadeRepository extends JpaRepository<Especialidades, Long>{
      
    
    Page<Especialidades> findByNome(String especialidade, Pageable pageable);

    Page<Especialidades> findByHospital(String hospital, Pageable pageable);

    @Query("SELECT e FROM Especialidades e WHERE e.nome = :especialidade AND e.hospital = :hospital")
    Page<Especialidades> findByNomeAndHospital(String especialidade, String hospital, Pageable pageable);

    
}
