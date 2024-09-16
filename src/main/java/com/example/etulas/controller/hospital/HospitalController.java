package com.example.etulas.controller.hospital;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.repository.hospital.HospitalRepository;
import com.example.etulas.service.Hospital.HospitalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@CacheConfig(cacheNames = "hospital")
@RequestMapping("hospital")
@Slf4j
@Tag(name = "hospital")
public class HospitalController {
    @Autowired
    HospitalService service;

    @Autowired
    HospitalRepository repository;

    

    @Cacheable
    @GetMapping
    @ResponseStatus(OK)
    @Operation(
        summary = "Listar Hospital",
        description = "Retorna um array com todos Hospitais cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Hospital retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<Hospital> buscarHospital() {
        log.info("Buscando hopitais");
        return service.buscarHospital();
    }



    @Operation(
        summary = "Listar Hospital por Id",
        description = "Retorna um hospital por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Hospital retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )   
    @GetMapping("{id}")
    public EntityModel<Hospital> buscarHospitalPorId(@PathVariable Long id) {
        log.info("Buscando Hospitais com o id {}", id);
        return service.buscarHospitalPorId(id);
    }

    @Operation(
        summary = "Listar paginação de hospital",
        description = "Retorna uma paginação de hospital"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Hospital retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    @GetMapping("page")
    public PagedModel<EntityModel<Hospital>> index(
        @RequestParam(required = false) String hospital,
        @PageableDefault(size = 5, direction = Direction.DESC) Pageable pageable
    ){
                 return service.index(hospital, pageable);
    }

    @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(
        summary = "Criar Hospital",
        description = "Cria um hospital com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Hospital criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<EntityModel<Hospital>> criarHospital(@Valid @RequestBody Hospital dados) {
        log.info("Criando hospital");
        return service.criarHospital(dados);
    
    }

    @Operation(
        summary = "Atualizar Hospital",
        description = "Atualiza hospital de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Hospital atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Hospital>> atualizarHospital(@PathVariable Long id, @RequestBody Hospital dados) {
        log.info("Atualizando hospital com o id {}", id);
        return service.atualizarHospital(id, dados);
    }


    @Operation(
        summary = "Deletar Hospital",
        description = "Deletar hospital com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Hospital deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deletarHospital(@PathVariable Long id) {
        log.info("Deletando hospital com o id", id);
        return service.deletarHospital(id);
    }
}
