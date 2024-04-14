package com.example.etulas.repository.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.hospital.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    
}
