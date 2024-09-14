package com.example.etulas.controller.Anamnesia;

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

import com.example.etulas.model.anamnesia.Anamnesia;
import com.example.etulas.service.anamnesia.AnamnesiaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("anamnesia")
@Slf4j
@Tag(name = "Anamnesia")
public class AnamnesiaController {
    @Autowired
    AnamnesiaService service;

    @GetMapping
    @ResponseStatus(OK)
    @Operation(summary = "Listar Anamnesia", description = "Retorna um array com todas anamnesias cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Anamnesia retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado anamnesia")
    })
    public List<Anamnesia> buscarAnamnesia() {
        log.info("Buscando Anamnesias");
        return service.buscarAnamnesia();
    }

    @GetMapping("{id}")
    @Operation(summary = "Listar Anamnesia por Id", description = "Retorna um anamnesia por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Anamnesia retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public ResponseEntity<Anamnesia> buscarAnamnesiaPorId(@PathVariable Long id) {
        log.info("Buscando anamnesia com o id {}", id);
        return service.buscarAnamnesiaPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Criar Anamnesia", description = "Cria uma anamnesia com os dados do corpo da requisição")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Anamnesia criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<Anamnesia> criarAnamnesia(@Valid @RequestBody Anamnesia dados) {
        log.info("Criando anamnesia");
        Anamnesia novaAnamnesia = service.criarAnamnesia(dados);
        return new ResponseEntity<>(novaAnamnesia, CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar Anamnesia", description = "Atualiza anamnesia de acordo com os dados no corpo da requisição")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Anamnesia atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public Anamnesia atualizarAnamnesia(@PathVariable Long id, @RequestBody Anamnesia dados) {
        log.info("Atualizando anamnesia com o id {}", id);
        return service.atualizarAnamnesia(id, dados);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    @Operation(summary = "Deletar Anamnesia", description = "Deletar anamnesia com id passado no path")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Anamnesia deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public void deletarAnamnesia(@PathVariable Long id) {
        service.apagarAnamnesia(id);
    }
}
