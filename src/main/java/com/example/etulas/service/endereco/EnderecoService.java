package com.example.etulas.service.endereco;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.endereco.Endereco;
import com.example.etulas.repository.endereco.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public Endereco salvarEndereco(Endereco dados) {
        Endereco novoEndereco = dados;
        return repository.save(novoEndereco);
    }

    public List<Endereco> buscarEndereco() {
        return repository.findAll();
    }

    public EntityModel<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        var endereco = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("endereço errado"));

        return endereco.toEntityModel();

    }

    public ResponseEntity<EntityModel<Endereco>> criarEndereco(Endereco dados) {
        Endereco novoEndereco = dados;
        repository.save(novoEndereco);

        return ResponseEntity
            .created(novoEndereco.toEntityModel().getRequiredLink("self").toUri())
                             .body(novoEndereco.toEntityModel());

    }

    public ResponseEntity<EntityModel<Endereco>> atualizarEndereco(Long id, Endereco dados) {
        verificarSeExiste(id);
        Endereco atualizadoEndereco = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        BeanUtils.copyProperties(dados, atualizadoEndereco, "id");
        repository.save(atualizadoEndereco);

        return ResponseEntity.ok(atualizadoEndereco.toEntityModel());
    }

    public ResponseEntity<Void> deletarEndereco(Long id) {
        verificarSeExiste(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
