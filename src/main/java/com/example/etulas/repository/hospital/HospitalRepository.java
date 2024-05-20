package com.example.etulas.repository.hospital;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.hospital.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    
    // Page<Hospital> findByHospitalNome(String nome, Pageable pageable);
}
