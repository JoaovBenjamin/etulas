package com.example.etulas.controller.paciente;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.etulas.dto.paciente.PacienteDTO;
import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.service.Paciente.PacienteService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("paciente")
@Slf4j
public class PacienteController {
    
    @Autowired 
    PacienteService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<Paciente> buscarPaciente(){
        log.info("Buscando pacientes");
        return service.buscarPaciente();
    }

    @GetMapping("{id}")
    public ResponseEntity<Paciente> buscarHospitalPorId(@PathVariable Long id){
        log.info("Buscando paciente com o id {}",id);
        return service.buscarPacientePorId(id);
    }

    @GetMapping("cpf/{cpf}")
    public List<Paciente> buscarPacientePorCpf(@PathVariable String cpf){
        log.info("Buscando paciente com cpf {}",cpf);
        return service.buscarPacientePorCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Paciente criarPaciente(@RequestBody PacienteDTO dados){
        log.info("Criando hospital");
        return service.criarPaciente(dados);
    }

    @PutMapping("{id}")
    public Paciente atualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO dados){
        log.info("Atualizando paciente com o id {}",id);
        return service.atualizarPaciente(id, dados);
    }
    

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletarHospital(@PathVariable Long id){
        log.info("Deletando paciente com o id", id);
        service.deletarPaciente(id);
    }

}
