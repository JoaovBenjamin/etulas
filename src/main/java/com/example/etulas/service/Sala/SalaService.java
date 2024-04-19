package com.example.etulas.service.Sala;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.sala.SalaDTO;
import com.example.etulas.model.sala.Sala;
import com.example.etulas.repository.sala.SalaRepository;

@Service
public class SalaService {

    @Autowired
    SalaRepository repository;

    public Sala salvarSala(SalaDTO dados) {
        Sala novaSala = new Sala(dados);
        return repository.save(novaSala);
    }

    public List<Sala> buscarSala() {
        return repository.findAll();
    }

    public ResponseEntity<Sala> buscarSalaPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public Sala criarSala(SalaDTO dados) {
        Sala novaSala = new Sala(dados);
        return repository.save(novaSala);
    }

    public Sala atualizarSala(Long id, SalaDTO dados) {
        verificarSeExiste(id);
        Sala atualizadaSala = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        BeanUtils.copyProperties(dados, atualizadaSala, "id");
        return repository.save(atualizadaSala);
    }

    public void apagarSala(Long id) {
        verificarSeExiste(id);
        Sala salaApagar = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        salaApagar.setAtivo(false);

    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
