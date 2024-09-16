package com.example.etulas.controller.sala;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.EntityModel;
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

import com.example.etulas.model.sala.Sala;
import com.example.etulas.service.Sala.SalaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "sala")
@Controller
@RestController
@RequestMapping("sala")
@Slf4j
@Tag(name = "Sala")
public class SalaController {
    @Autowired
    SalaService service;

    @Cacheable
    @GetMapping
    @ResponseStatus(OK)
      @Operation(
        summary = "Listar Sala",
        description = "Retorna um array com todas as salas cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Sala retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<Sala> buscarSala() {
        log.info("Buscando Salas");
        return service.buscarSala();
    }

    @Operation(
        summary = "Listar sala por Id",
        description = "Retorna uma sala por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Sala retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    @GetMapping("{id}")
    public EntityModel<Sala> buscarSalaPorId(@PathVariable Long id) {
        log.info("Buscando sala com o id {}", id);
        return service.buscarSalaPorId(id);
    }

    @CacheEvict(allEntries = true)
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(
        summary = "Criar Sala",
        description = "Cria uma sala com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Sala criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<EntityModel<Sala>> criarSala(@Valid @RequestBody Sala dados) {
        log.info("Criando sala");
        return service.criarSala(dados);
    }

    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar Sala",
        description = "Atualiza sala de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Saça atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<EntityModel<Sala>> atualizarSala(@PathVariable Long id, @RequestBody Sala dados) {
        log.info("Atualizando sala com o id {}", id);
        return service.atualizarSala(id, dados);
    }

    @Operation(
        summary = "Deletar Sala",
        description = "Deletar sala com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Hospital deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        return service.apagarSala(id);
    }
}
