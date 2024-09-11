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

import com.example.etulas.model.equipamentosMedicos.Equipamentos;
import com.example.etulas.repository.equipamentos.EquipamentosRepository;
import com.example.etulas.service.equipamentos.EquipamentosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@CacheConfig(cacheNames = "equipamentos")
@RestController
@RequestMapping("equipamentos")
@Slf4j
@Tag(name = "Equipamentos")
public class EquipamentosController {
    @Autowired
    EquipamentosService service;

    @Autowired
    EquipamentosRepository repository;

    @Cacheable
    @GetMapping
    @ResponseStatus(OK)
     @Operation(
        summary = "Listar Equipamentos",
        description = "Retorna um array com todos equipamentos cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Equipamentos retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<Equipamentos> buscarEquipamentos() {
        log.info("Buscando Equipamentos");
        return service.buscarEquipamentos();
    }

    @GetMapping("page")
    @Operation(
        summary = "Listar paginação de Equipamento",
        description = "Retorna uma paginação de equipamento"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Equipamento retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
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
    @Operation(
        summary = "Listar Equipamento por Id",
        description = "Retorna um equipamento por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Equipamento retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )   
    public ResponseEntity<Equipamentos> buscarEquipamentosPorId(@PathVariable Long id) {
        log.info("Buscando equipamento com o id {}", id);
        return service.buscarEquipamentosPorId(id);
    }

    @CacheEvict(allEntries = true)
    @PostMapping
    @Operation(
        summary = "Criar Equipamento",
        description = "Cria um equipamento com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Equipamento criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @ResponseStatus(CREATED)
    public ResponseEntity<Equipamentos> criarEquipamento(@Valid @RequestBody Equipamentos dados) {
        log.info("Criando equipamento");
        Equipamentos novoEquipamento = service.criarEquipamentos(dados);
        return new ResponseEntity<>(novoEquipamento, CREATED);
    }


    @CacheEvict(allEntries = true)
    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar Equipamento",
        description = "Atualiza equipamento de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Equipamento atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public Equipamentos atualizarEquipamento(@PathVariable Long id, @RequestBody Equipamentos dados) {
        log.info("Atualizando equipamento com o id {}", id);
        return service.atualizarEquipamentos(id, dados);
    }

    @CacheEvict(allEntries = true)
    @DeleteMapping("{id}")
    @Operation(
        summary = "Deletar Equipamento",
        description = "Deletar equipametno com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Hospital deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @ResponseStatus(NOT_FOUND)
    public void deletarEquipamento(@PathVariable Long id) {
        service.apagarEquipamentos(id);
    }
}
