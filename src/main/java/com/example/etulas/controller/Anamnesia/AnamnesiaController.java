package com.example.etulas.controller.Anamnesia;

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

import com.example.etulas.dto.anamnesia.AnamnesiaDTO;
import com.example.etulas.model.anamnesia.Anamnesia;
import com.example.etulas.service.anamnesia.AnamnesiaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("anamnesia")
@Slf4j
public class AnamnesiaController {
    @Autowired
    AnamnesiaService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<Anamnesia> buscarAnamnesia() {
        log.info("Buscando Anamnesias");
        return service.buscarAnamnesia();
    }

    @GetMapping("{id}")
    public ResponseEntity<Anamnesia> buscarAnamnesiaPorId(@PathVariable Long id) {
        log.info("Buscando anamnesia com o id {}", id);
        return service.buscarAnamnesiaPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Anamnesia> criarAnamnesia(@RequestBody AnamnesiaDTO dados) {
        log.info("Criando anamnesia");
        Anamnesia novaAnamnesia = new Anamnesia(dados);
        return new ResponseEntity<>(novaAnamnesia, CREATED);
    }

    @PutMapping("{id}")
    public Anamnesia atualizarAnamnesia(@PathVariable Long id) {
        log.info("Atualizando anamnesia com o id {}", id);
        return service.atualizarAnamnesia(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarAnamnesia(@PathVariable Long id) {
        service.apagarAnamnesia(id);
    }
}
