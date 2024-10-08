package com.example.etulas.controller.especialidades;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.repository.especialidade.EspecialidadeRepository;
import com.example.etulas.service.especialidades.EspecialidadesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "especialidades")
@Controller
@RestController
@RequestMapping("especialidades")
@Slf4j
@Tag(name = "Especialidades")
public class EspecialidadesController {
    @Autowired
    EspecialidadesService service;

    @Autowired
    EspecialidadeRepository repository;

    @Cacheable
    @GetMapping
    @ResponseStatus(OK)
    @Operation(
        summary = "Listar Especialidade",
        description = "Retorna um array com todas especialidades cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Especialidades retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<Especialidades> buscarEspecialidades() {
        log.info("Buscando Especialidades");
        return service.buscarEspecialidades();
    }

    @Operation(
        summary = "Listar paginação de especialidades",
        description = "Retorna uma paginação de especialidades"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Especialidades retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    @GetMapping("page")
    public Page<Especialidades> index(
        @RequestParam(required = false) String especialidade,
        @RequestParam(required = false) String hospital,
        @PageableDefault(size = 5, direction = Direction.DESC) Pageable pageable
    ){

        if(especialidade !=null && hospital !=null){
            return repository.findByNomeAndHospital(especialidade,hospital,pageable);
        }

        if(especialidade != null){
            return repository.findByNome(especialidade, pageable);
        }
        
        if (hospital != null) {
            return repository.findByHospital(hospital, pageable);
        }
        return repository.findAll(pageable);
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar Especialidades por Id",
        description = "Retorna um especialidades por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "especialiadess retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )  
    public ResponseEntity<Especialidades> buscarEspecialidadePorId(@Valid @PathVariable Long id) {
        log.info("Buscando especialidade com o id {}", id);
        return service.buscarEspecialidadePorId(id);
    }

    @CacheEvict(allEntries = true)
    @PostMapping
    @Operation(
        summary = "Criar Especialidades",
        description = "Cria uma especialidades com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Especialidades criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @ResponseStatus(CREATED)
    public ResponseEntity<EntityModel<Especialidades>> criarEspecialidade(@RequestBody Especialidades dados) {
        log.info("Criando especialidade");
        return service.criarEspecialidade(dados);
    }

    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar Especialidades",
        description = "Atualiza especialidades de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Especialidades atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<EntityModel<Especialidades>> atualizarEspecialidade(@PathVariable Long id, @RequestBody Especialidades dados) {
        log.info("Atualizando especialidade com o id {}", id);
        return service.atualizarEspecialidade(id, dados);
    }

    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @Operation(
        summary = "Deletar Especialidades",
        description = "Deletar especialidades com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Especialidades deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Void> deletarEspecialidade(@PathVariable Long id) {
       return service.apagarEspecialidade(id);
    }
}
    