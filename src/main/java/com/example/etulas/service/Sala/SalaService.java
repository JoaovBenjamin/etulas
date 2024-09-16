package com.example.etulas.service.Sala;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.sala.Sala;
import com.example.etulas.repository.sala.SalaRepository;

@Service
public class SalaService {

    @Autowired
    SalaRepository repository;

    public Sala salvarSala(Sala dados) {
        Sala novaSala = dados;
        return repository.save(novaSala);
    }

    public List<Sala> buscarSala() {
        return repository.findAll();
    }

    public EntityModel<Sala> buscarSalaPorId(@PathVariable Long id) {
        var sala = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sala não encontrada"));

        return sala.toEntityModel();
    }

    public ResponseEntity<EntityModel<Sala>> criarSala(Sala dados) {
        Sala novaSala = dados;
        repository.save(novaSala);

        return ResponseEntity
                       .created(novaSala.toEntityModel().getRequiredLink("self").toUri())
                       .body(novaSala.toEntityModel());
    }

    public ResponseEntity<EntityModel<Sala>> atualizarSala(Long id, Sala dados) {
        verificarSeExiste(id);
        Sala atualizadaSala = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        BeanUtils.copyProperties(dados, atualizadaSala, "id");
        repository.save(atualizadaSala);

        return ResponseEntity.ok(atualizadaSala.toEntityModel());
    }

    public ResponseEntity<Void> apagarSala(Long id) {
        verificarSeExiste(id);
        Sala salaApagar = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        salaApagar.setAtivo(false);

        return ResponseEntity.noContent().build();

    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
