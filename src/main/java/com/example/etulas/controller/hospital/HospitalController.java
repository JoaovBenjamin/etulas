package com.example.etulas.controller.hospital;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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

import com.example.etulas.dto.hospital.HospitalDTO;
import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.service.Hospital.HospitalService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("hospital")
@Slf4j
public class HospitalController {
    @Autowired
    HospitalService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<Hospital> buscarHospital() {
        log.info("Buscando hopitais");
        return service.buscarHospital();
    }

    @GetMapping("{id}")
    public ResponseEntity<Hospital> buscarHospitalPorId(@PathVariable Long id) {
        log.info("Buscando Hospitais com o id {}", id);
        return service.buscarHospitalPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Hospital> criarHospital(@RequestBody HospitalDTO dados) {
        log.info("Criando hospital");
        Hospital hospital = service.criarHospital(dados);
        return new ResponseEntity<>(hospital, CREATED);
    }

    @PutMapping("{id}")
    public Hospital atualizarHospital(@PathVariable Long id) {
        log.info("Atualizando hospital com o id {}", id);
        return service.atualizarHospital(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletarHospital(@PathVariable Long id) {
        log.info("Deletando hospital com o id", id);
        service.deletarHospital(id);
    }
}
