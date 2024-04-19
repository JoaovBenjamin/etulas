package com.example.etulas.controller.endereco;

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

import com.example.etulas.dto.endereco.EnderecoDTO;
import com.example.etulas.model.endereco.Endereco;
import com.example.etulas.service.endereco.EnderecoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("endereco")
@Slf4j
public class EnderecoController {
    @Autowired
    EnderecoService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<Endereco> buscarEndereco() {
        log.info("Buscando Endereços");
        return service.buscarEndereco();
    }

    @GetMapping("{id}")
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        log.info("Buscando endereco com o id {}", id);
        return service.buscarEnderecoPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Endereco> criarHospital(@Valid @RequestBody EnderecoDTO dados) {
        log.info("Criando endereco");
        Endereco novEndereco = new Endereco(dados);
        return new ResponseEntity<>(novEndereco, CREATED);
    }

    @PutMapping("{id}")
    public Endereco atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO dados) {
        log.info("Atualizando endereco com o id {}", id);
        return service.atualizarEndereco(id,dados);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarEndereco(Long id) {
        service.deletarEndereco(id);
    }
}
