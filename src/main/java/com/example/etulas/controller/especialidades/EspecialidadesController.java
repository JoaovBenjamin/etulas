package com.example.etulas.controller.especialidades;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
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

import com.example.etulas.dto.especialidades.EspecialidadesDTO;
import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.service.especialidades.EspecialidadesService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("especialidades")
@Slf4j
public class EspecialidadesController {
    @Autowired
    EspecialidadesService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<Especialidades> buscarEspecialidades() {
        log.info("Buscando Especialidades");
        return service.buscarEspecialidades();
    }

    @GetMapping("{id}")
    public ResponseEntity<Especialidades> buscarEspecialidadePorId(@Valid @PathVariable Long id) {
        log.info("Buscando especialidade com o id {}", id);
        return service.buscarEspecialidadePorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Especialidades> criarEspecialidade(@RequestBody EspecialidadesDTO dados) {
        log.info("Criando especialidade");
        Especialidades novaEspecialidade = service.criarEspecialidade(dados);
        return new ResponseEntity<>(novaEspecialidade, CREATED);
    }

    @PutMapping("{id}")
    public Especialidades atualizarEspecialidade(@PathVariable Long id, @RequestBody EspecialidadesDTO dados) {
        log.info("Atualizando especialidade com o id {}", id);
        return service.atualizarEspecialidade(id, dados);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarEspecialidade(@PathVariable Long id) {
        service.apagarEspecialidade(id);
    }
}
