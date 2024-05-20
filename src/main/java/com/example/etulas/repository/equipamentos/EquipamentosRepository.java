package com.example.etulas.repository.equipamentos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.etulas.model.equipamentosMedicos.Equipamentos;

public interface EquipamentosRepository extends JpaRepository<Equipamentos, Long>{
    
    Page<Equipamentos> findByNome(String equipamento, Pageable pageable);

    Page<Equipamentos> findByHospital(String hospital, Pageable pageable);

    @Query("SELECT e FROM Equipamentos e WHERE e.nome = :nome AND e.hospital = :hospital")
    Page<Equipamentos> findByNomeAndHospital(String nome, String hospital, Pageable pageable);
}
