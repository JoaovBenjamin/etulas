package com.example.etulas.service.Paciente;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.paciente.PacienteDTO;
import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.repository.paciente.PacienteRepository;

@Service
public class PacienteService {
    
    @Autowired
    PacienteRepository repository;

    public List<Paciente> buscarPaciente(){
        return repository.findAll();
    }

    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public List<Paciente> buscarPacientePorCpf(@PathVariable String cpf){
        return repository.findByCpf(cpf);
    }

    public Paciente criarPaciente(PacienteDTO dados){
        Paciente novoPaciente = new Paciente(dados);
        return repository.save(novoPaciente);

    }

    public Paciente atualizarPaciente(Long id, PacienteDTO dados){
        verificarSeExiste(id);
        Paciente atualizadoPaciente = new Paciente(dados);
        return repository.save(atualizadoPaciente);
    }

    public void deletarPaciente(Long id){
        verificarSeExiste(id);
        repository.deleteById(id);
    }

    public void verificarSeExiste(Long id){
        repository.
                    findById(id)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
