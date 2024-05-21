package com.example.etulas.controller.fichaAtendimento;

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

import com.example.etulas.dto.fichaAtendimento.FichaAtendimentoDTO;
import com.example.etulas.model.fichaAtendimento.FichaDeAtendimento;
import com.example.etulas.service.fichaDeAtendimento.FichaDeAtendimentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("fichadeatendimento")
@Slf4j
@Tag(name = "Ficha de atendimento")
public class FichaDeAtendimentoController {
    @Autowired
    FichaDeAtendimentoService service;

    @GetMapping
    @ResponseStatus(OK)
     @Operation(
        summary = "Listar Ficha de Atendimento",
        description = "Retorna um array com todas as fichas cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Ficha retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<FichaDeAtendimento> buscarFichasDeAtendimento() {
        log.info("Buscando Fichas de Atendimento");
        return service.buscarFichasDeAtendimento();
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Listar Ficha de Atendimento por Id",
        description = "Retorna uma ficha por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Ficha retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )   
    public ResponseEntity<FichaDeAtendimento> buscarFichaDeAtendimentoPorId(@PathVariable Long id) {
        log.info("Buscando ficha de atendimento com o id {}", id);
        return service.buscarFichaDeAtendimentoPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(
        summary = "Criar Ficha de Atendimetno",
        description = "Cria uma ficha com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Ficha criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<FichaDeAtendimento> criarFichaDeAtendimento(@Valid @RequestBody FichaAtendimentoDTO dados) {
        log.info("Criando ficha de atendimento");
        FichaDeAtendimento novaFicha = service.criarFichaDeAtendimento(dados);
        return new ResponseEntity<>(novaFicha, CREATED);
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar Ficha de Atendimento",
        description = "Atualiza ficha de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ficha atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public FichaDeAtendimento atualizarFichaDeAtendimento(@PathVariable Long id, FichaAtendimentoDTO dados) {
        log.info("Atualizando ficha de atendimento com o id {}", id);
        return service.atualizarFichaDeAtendimento(id, dados);
    }

    @DeleteMapping("{id}")
    @Operation(
        summary = "Deletar Ficha de Atendimeto",
        description = "Deletar ficha com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Ficha deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @ResponseStatus(NOT_FOUND)
    public void deletarFichaDeAtendimento(@PathVariable Long id) {
        service.apagarFichaDeAtendimento(id);
    }
}
