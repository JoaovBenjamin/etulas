package com.example.etulas.repository.sala;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.sala.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    List<Sala> findByNumeroDaSala(String numeroDaSala);
}
