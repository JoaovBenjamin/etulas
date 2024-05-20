package com.example.etulas.controller.equipamentos;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import com.example.etulas.dto.equipamentos.EquipamentosDTO;
import com.example.etulas.model.equipamentosMedicos.Equipamentos;
import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.repository.equipamentos.EquipamentosRepository;
import com.example.etulas.service.equipamentos.EquipamentosService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@CacheConfig(cacheNames = "equipamentos")
@RestController
@RequestMapping("equipamentos")
@Slf4j
public class EquipamentosController {
    @Autowired
    EquipamentosService service;

    @Autowired
    EquipamentosRepository repository;

    @Cacheable
    @GetMapping
    @ResponseStatus(OK)
    public List<Equipamentos> buscarEquipamentos() {
        log.info("Buscando Equipamentos");
        return service.buscarEquipamentos();
    }

            @GetMapping("page")
    public Page<Equipamentos> index(
        @RequestParam(required = false) String equipamento,
        @RequestParam(required = false) String  hospital,
        @PageableDefault(size = 5, direction = Direction.DESC) Pageable pageable
    ){

        if(equipamento !=null && hospital !=null){
            return repository.findByNomeAndHospital(equipamento,hospital, pageable);
        }

        if(equipamento !=null){
            return repository.findByNome(equipamento, pageable);
        }

        if (hospital != null) {
            return repository.findByHospital(hospital, pageable);
        }
    
        return repository.findAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Equipamentos> buscarEquipamentosPorId(@PathVariable Long id) {
        log.info("Buscando equipamento com o id {}", id);
        return service.buscarEquipamentosPorId(id);
    }

    @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Equipamentos> criarEquipamento(@Valid @RequestBody EquipamentosDTO dados) {
        log.info("Criando equipamento");
        Equipamentos novoEquipamento = service.criarEquipamentos(dados);
        return new ResponseEntity<>(novoEquipamento, CREATED);
    }


    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    public Equipamentos atualizarEquipamento(@PathVariable Long id, @RequestBody EquipamentosDTO dados) {
        log.info("Atualizando equipamento com o id {}", id);
        return service.atualizarEquipamentos(id, dados);
    }

    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarEquipamento(@PathVariable Long id) {
        service.apagarEquipamentos(id);
    }
}
