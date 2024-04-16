package com.example.etulas.controller.equipamentos;

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

import com.example.etulas.dto.equipamentos.EquipamentosDTO;
import com.example.etulas.model.equipamentosMedicos.Equipamentos;
import com.example.etulas.service.equipamentos.EquipamentosService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("equipamentos")
@Slf4j
public class EquipamentosController {
    @Autowired
    EquipamentosService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<Equipamentos> buscarEquipamentos() {
        log.info("Buscando Equipamentos");
        return service.buscarEquipamentos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Equipamentos> buscarEquipamentosPorId(@PathVariable Long id) {
        log.info("Buscando equipamento com o id {}", id);
        return service.buscarEquipamentosPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Equipamentos> criarEquipamento(@RequestBody EquipamentosDTO dados) {
        log.info("Criando equipamento");
        Equipamentos novoEquipamento = new Equipamentos(dados);
        return new ResponseEntity<>(novoEquipamento, CREATED);
    }

    @PutMapping("{id}")
    public Equipamentos atualizarEquipamento(@PathVariable Long id) {
        log.info("Atualizando equipamento com o id {}", id);
        return service.atualizarEquipamentos(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarEquipamento(@PathVariable Long id) {
        service.apagarEquipamentos(id);
    }
}
