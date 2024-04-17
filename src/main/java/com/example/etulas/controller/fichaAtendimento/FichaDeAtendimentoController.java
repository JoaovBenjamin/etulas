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

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("fichadeatendimento")
@Slf4j
public class FichaDeAtendimentoController {
    @Autowired
    FichaDeAtendimentoService service;

    @GetMapping
    @ResponseStatus(OK)
    public List<FichaDeAtendimento> buscarFichasDeAtendimento() {
        log.info("Buscando Fichas de Atendimento");
        return service.buscarFichasDeAtendimento();
    }

    @GetMapping("{id}")
    public ResponseEntity<FichaDeAtendimento> buscarFichaDeAtendimentoPorId(@PathVariable Long id) {
        log.info("Buscando ficha de atendimento com o id {}", id);
        return service.buscarFichaDeAtendimentoPorId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<FichaDeAtendimento> criarFichaDeAtendimento(@Valid @RequestBody FichaAtendimentoDTO dados) {
        log.info("Criando ficha de atendimento");
        FichaDeAtendimento novaFicha = new FichaDeAtendimento(dados);
        return new ResponseEntity<>(novaFicha, CREATED);
    }

    @PutMapping("{id}")
    public FichaDeAtendimento atualizarFichaDeAtendimento(@PathVariable Long id) {
        log.info("Atualizando ficha de atendimento com o id {}", id);
        return service.atualizarFichaDeAtendimento(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NOT_FOUND)
    public void deletarFichaDeAtendimento(@PathVariable Long id) {
        service.apagarFichaDeAtendimento(id);
    }
}
