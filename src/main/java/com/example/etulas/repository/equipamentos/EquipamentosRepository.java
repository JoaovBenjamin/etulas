package com.example.etulas.repository.equipamentos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.equipamentosMedicos.Equipamentos;

public interface EquipamentosRepository extends JpaRepository<Equipamentos, Long>{
    
}
