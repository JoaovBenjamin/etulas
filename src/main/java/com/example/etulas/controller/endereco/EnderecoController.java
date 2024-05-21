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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("endereco")
@Slf4j
@Tag(name = "Endereço")
public class EnderecoController {
    @Autowired
    EnderecoService service;

    @GetMapping
    @ResponseStatus(OK)
     @Operation(
        summary = "Listar Endereço",
        description = "Retorna um array com todos endereços cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Endereços retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<Endereco> buscarEndereco() {
        log.info("Buscando Endereços");
        return service.buscarEndereco();
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar Endereço por Id",
        description = "Retorna um endereço por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )   
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        log.info("Buscando endereco com o id {}", id);
        return service.buscarEnderecoPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(
        summary = "Criar Endereço",
        description = "Cria um endereço com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<Endereco> criarHospital(@Valid @RequestBody EnderecoDTO dados) {
        log.info("Criando endereco");
        Endereco novEndereco = new Endereco(dados);
        return new ResponseEntity<>(novEndereco, CREATED);
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar Endereço",
        description = "Atualiza endereço de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public Endereco atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO dados) {
        log.info("Atualizando endereco com o id {}", id);
        return service.atualizarEndereco(id,dados);
    }

    @Operation(
        summary = "Deletar Endereço",
        description = "Deletar endereço com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarEndereco(Long id) {
        service.deletarEndereco(id);
    }
}
