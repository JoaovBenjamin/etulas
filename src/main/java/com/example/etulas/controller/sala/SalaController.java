package com.example.etulas.controller.sala;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import com.example.etulas.dto.sala.SalaDTO;
import com.example.etulas.model.sala.Sala;
import com.example.etulas.service.Sala.SalaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "sala")
@Controller
@RestController
@RequestMapping("sala")
@Slf4j
public class SalaController {
    @Autowired
    SalaService service;

    @Cacheable
    @GetMapping
    @ResponseStatus(OK)
    public List<Sala> buscarSala() {
        log.info("Buscando Salas");
        return service.buscarSala();
    }

    @GetMapping("{id}")
    public ResponseEntity<Sala> buscarSalaPorId(@PathVariable Long id) {
        log.info("Buscando sala com o id {}", id);
        return service.buscarSalaPorId(id);
    }

    @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Sala> criarSala(@Valid @RequestBody SalaDTO dados) {
        log.info("Criando sala");
        Sala novaSala = service.criarSala(dados);
        return new ResponseEntity<>(novaSala, CREATED);
    }

    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    public Sala atualizarSala(@PathVariable Long id, @RequestBody SalaDTO dados) {
        log.info("Atualizando sala com o id {}", id);
        return service.atualizarSala(id, dados);
    }

    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarSala(@PathVariable Long id) {
        service.apagarSala(id);
    }
}
