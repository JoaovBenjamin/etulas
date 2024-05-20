package com.example.etulas.controller.paciente;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.etulas.dto.paciente.PacienteDTO;
import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.repository.paciente.PacienteRepository;
import com.example.etulas.service.Paciente.PacienteService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("paciente")
@Slf4j
public class PacienteController {

    @Autowired
    PacienteService service;

    @Autowired
    PacienteRepository repository;

    @GetMapping
    @ResponseStatus(OK)
    public List<Paciente> buscarPaciente() {
        log.info("Buscando pacientes");
        return service.buscarPaciente();
    }

        @GetMapping("page")
    public Page<Paciente> index(
        @RequestParam(required = false) String paciente,
        @RequestParam(required = false) String cpf,
        @PageableDefault(size = 5, direction = Direction.DESC) Pageable pageable
    ){

        if(paciente !=null && cpf !=null){
            return repository.findByNomeAndCpf(paciente,cpf, pageable);
        }

        if(paciente !=null){
            return repository.findByNome(paciente, pageable);
        }

        if (cpf != null) {
            return repository.findByCpf(cpf, pageable);
        }
    
        return repository.findAll(pageable);
    }


    @GetMapping("{id}")
    public ResponseEntity<Paciente> buscarHospitalPorId(@PathVariable Long id) {
        log.info("Buscando paciente com o id {}", id);
        return service.buscarPacientePorId(id);
    }

    @GetMapping("cpf/{cpf}")
    public List<Paciente> buscarPacientePorCpf(@PathVariable String cpf) {
        log.info("Buscando paciente com cpf {}", cpf);
        return service.buscarPacientePorCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Paciente> criarPaciente(@Valid @RequestBody PacienteDTO dados) {
        log.info("Criando hospital");
        Paciente novoPaciente = service.criarPaciente(dados);
        return new ResponseEntity<>(novoPaciente, CREATED);
    }

    @PutMapping("{id}")
    public Paciente atualizarPaciente(@PathVariable Long id, PacienteDTO dados) {
        log.info("Atualizando paciente com o id {}", id);
        return service.atualizarPaciente(id, dados);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletarHospital(@PathVariable Long id) {
        log.info("Deletando paciente com o id", id);
        service.deletarPaciente(id);
    }

}
