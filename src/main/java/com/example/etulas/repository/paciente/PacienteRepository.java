package com.example.etulas.repository.paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.etulas.model.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByCpf(String cpf);
}
