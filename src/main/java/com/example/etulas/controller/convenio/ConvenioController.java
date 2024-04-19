package com.example.etulas.controller.convenio;

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

import com.example.etulas.dto.convenio.ConvenioDTO;
import com.example.etulas.model.convenio.Convenio;
import com.example.etulas.service.convenio.ConvenioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("convenio")
@Slf4j
public class ConvenioController {
    @Autowired
    ConvenioService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<Convenio> buscarConvenio() {
        log.info("Buscando Convenios");
        return service.buscarConvenio();
    }

    @GetMapping("{id}")
    public ResponseEntity<Convenio> buscarConvenioPorId(@PathVariable Long id) {
        log.info("Buscando endereco com o id {}", id);
        return service.buscarConvenioPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Convenio> criarConvenio(@Valid @RequestBody ConvenioDTO dados) {
        log.info("Criando convenio");
        Convenio novoConvenio = service.criarConvenio(dados);
        return new ResponseEntity<>(novoConvenio, CREATED);
    }

    @PutMapping("{id}")
    public Convenio atualizarEndereco(@PathVariable Long id, @RequestBody ConvenioDTO dados) {
        log.info("Atualizando endereco com o id {}", id);
        return service.atualizarConvenio(id,dados);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarEndereco(@PathVariable Long id) {
        service.apagarConvenio(id);
    }
}
