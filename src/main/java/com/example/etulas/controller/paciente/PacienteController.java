package com.example.etulas.controller.paciente;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

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

import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.repository.paciente.PacienteRepository;
import com.example.etulas.service.Paciente.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("paciente")
@Slf4j
@Tag(name = "Paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    PacienteRepository repository;


    @GetMapping
    @ResponseStatus(OK)
     @Operation(
        summary = "Listar Paciente",
        description = "Retorna um array com todos pacientes cadastrados."         
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Pacientes retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public List<Paciente> buscarPaciente() {
        log.info("Buscando pacientes");
        return service.buscarPaciente();
    }

    @GetMapping("page")
    @Operation(
        summary = "Listar paginação de pacientes",
        description = "Retorna uma paginação de pacientes"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Pacientes retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )
    public PagedModel<EntityModel<Paciente>> index(
        @RequestParam(required = false) String paciente,
        @RequestParam(required = false) String cpf,
        @PageableDefault(size = 5, direction = Direction.DESC) Pageable pageable
    ){
          return service.index(paciente, cpf, pageable);
    }


    @GetMapping("{id}")
    @Operation(
        summary = "Listar Paciente por Id",
        description = "Retorna um paciente por id"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Paciente retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )  
    public EntityModel<Paciente> buscarPacientePorId(@PathVariable Long id) {
        log.info("Buscando paciente com o id {}", id);
        return service.buscarPacientePorId(id);
    }

    @GetMapping("cpf/{cpf}")
    @Operation(
        summary = "Listar paciente por cpf",
        description = "Retorna um paciente de acordo com cpf"
    )
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Paciente retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
        }
    )  
    public ResponseEntity<EntityModel<Paciente>> buscarPacientePorCpf(@PathVariable String cpf) {
        log.info("Buscando paciente com cpf {}", cpf);
        return service.buscarPacientePorCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(
        summary = "Criar Paciente",
        description = "Cria um paciente com os dados do corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<EntityModel<Paciente>> criarPaciente(@Valid @RequestBody Paciente dados) {
        log.info("Criando hospital");

        rabbitTemplate.convertAndSend("email-queue", "Novo paciente cadastrado" );

        return service.criarPaciente(dados);
    }

    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar Paciente",
        description = "Atualiza paciente de acordo com os dados no corpo da requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição")
    })
    public ResponseEntity<EntityModel<Paciente>> atualizarPaciente(@PathVariable Long id, Paciente dados) {
        log.info("Atualizando paciente com o id {}", id);
        return service.atualizarPaciente(id, dados);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
        summary = "Deletar Paciente",
        description = "Deletar paciente com id passado no path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Paciente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        log.info("Deletando paciente com o id", id);
        return service.deletarPaciente(id);
    }

}
