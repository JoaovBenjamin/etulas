package com.example.etulas.repository.paciente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.etulas.model.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByCpf(String cpf);

    @Query("SELECT p FROM Paciente p WHERE p.nome = :nome AND p.cpf = :cpf")
    Page<Paciente> findByNomeAndCpf(String nome, String cpf, Pageable pageable);

    Page<Paciente> findByCpf(String cpf, Pageable pageable);

    Page<Paciente> findByNome(String paciente, Pageable pageable);
}
