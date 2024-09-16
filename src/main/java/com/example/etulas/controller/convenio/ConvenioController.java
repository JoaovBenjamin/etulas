package com.example.etulas.controller.convenio;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.etulas.model.convenio.Convenio;
import com.example.etulas.service.convenio.ConvenioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("convenio")
@Slf4j
@Tag(name = "Convenio")
public class ConvenioController {
    @Autowired
    ConvenioService service;

    @GetMapping
    @ResponseStatus(OK)
      @Operation(
        summary = "Listar Convenio",
        description = "Retorna um array com todos convenios cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Convenios retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<Convenio> buscarConvenio() {
        log.info("Buscando Convenios");
        return service.buscarConvenio();
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar Convenio por Id",
        description = "Retorna um convenio por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Convenio retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )   
    public EntityModel<Convenio> buscarConvenioPorId(@PathVariable Long id) {
        log.info("Buscando endereco com o id {}", id);
        return service.buscarConvenioPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(
        summary = "Criar Convenio",
        description = "Cria um convenio com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Convenio criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<EntityModel<Convenio>> criarConvenio(@Valid @RequestBody Convenio dados) {
        log.info("Criando convenio");
        return service.criarConvenio(dados);
    }

    @Operation(
        summary = "Atualizar Convenio",
        description = "Atualiza convenio de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Convenio atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Convenio>> atualizarEndereco(@PathVariable Long id, @RequestBody Convenio dados) {
        log.info("Atualizando endereco com o id {}", id);
        return service.atualizarConvenio(id,dados);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    @Operation(
        summary = "Deletar Convenio",
        description = "Deletar convenio com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Convenio deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        return service.apagarConvenio(id);
    }
}
