package com.example.etulas.service.anamnesia;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.anamnesia.Anamnesia;
import com.example.etulas.repository.anamnesia.AnamnesiaRepository;

@Service
public class AnamnesiaService {

    @Autowired
    AnamnesiaRepository repository;

    public Anamnesia salvarAnamnesia(Anamnesia dados) {
        Anamnesia novaAnamnesia = dados;
        return repository.save(novaAnamnesia);
    }

    public List<Anamnesia> buscarAnamnesia() {
        return repository.findAll();
    }

    public EntityModel<Anamnesia> buscarAnamnesiaPorId(@PathVariable Long id) {
          var anamnesia = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Anamnesia não encontrada"));

          return anamnesia.toEntityModel();

    }

    public ResponseEntity<EntityModel<Anamnesia>> criarAnamnesia(Anamnesia dados) {
        Anamnesia novaAnamnesia = dados;
       repository.save(novaAnamnesia);

       return ResponseEntity
                         .created(novaAnamnesia.toEntityModel().getRequiredLink("self").toUri())
                         .body(novaAnamnesia.toEntityModel());
    }

    public ResponseEntity<EntityModel<Anamnesia>> atualizarAnamnesia(Long id, Anamnesia dados) {
        verificarSeExiste(id);
        Anamnesia atualizadaAnamnesia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anamnésia não encontrada"));
        BeanUtils.copyProperties(dados, atualizadaAnamnesia, "id");
        repository.save(atualizadaAnamnesia);

        return ResponseEntity
                     .ok(atualizadaAnamnesia.toEntityModel());

    }

    public ResponseEntity<Void> apagarAnamnesia(Long id) {
        verificarSeExiste(id);
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
