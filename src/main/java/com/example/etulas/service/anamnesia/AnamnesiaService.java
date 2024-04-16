package com.example.etulas.service.anamnesia;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.anamnesia.AnamnesiaDTO;
import com.example.etulas.model.anamnesia.Anamnesia;
import com.example.etulas.repository.anamnesia.AnamnesiaRepository;

@Service
public class AnamnesiaService {

    @Autowired
    AnamnesiaRepository repository;

    public Anamnesia salvarAnamnesia(AnamnesiaDTO dados) {
        Anamnesia novaAnamnesia = new Anamnesia(dados);
        return repository.save(novaAnamnesia);
    }

    public List<Anamnesia> buscarAnamnesia() {
        return repository.findAll();
    }

    public ResponseEntity<Anamnesia> buscarAnamnesiaPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public Anamnesia criarAnamnesia(AnamnesiaDTO dados) {
        Anamnesia novaAnamnesia = new Anamnesia(dados);
        return repository.save(novaAnamnesia);
    }

    public Anamnesia atualizarAnamnesia(Long id) {
        verificarSeExiste(id);
        Anamnesia atualizadaAnamnesia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anamnésia não encontrada"));
        atualizadaAnamnesia.setId(id);
        return repository.save(atualizadaAnamnesia);
    }

    public void apagarAnamnesia(Long id) {
        verificarSeExiste(id);
        repository.deleteById(id);
    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
