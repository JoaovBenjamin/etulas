package com.example.etulas.service.convenio;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.convenio.Convenio;
import com.example.etulas.repository.convenio.ConvenioRepository;

@Service
public class ConvenioService {

    @Autowired
    ConvenioRepository repository;

    public Convenio salvarConvenio(Convenio dados) {
        Convenio novoConvenio = dados;
        return repository.save(novoConvenio);
    }

    public List<Convenio> buscarConvenio() {
        return repository.findAll();
    }

    public EntityModel<Convenio> buscarConvenioPorId(@PathVariable Long id) {
       var convenio = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Convenio não encontrado"));

       return convenio.toEntityModel();

    }

    public ResponseEntity<EntityModel<Convenio>> criarConvenio(Convenio dados) {
        Convenio novoConvenio = dados;
        repository.save(novoConvenio);

        return ResponseEntity
                           .created(novoConvenio.toEntityModel().getRequiredLink("self").toUri())
                           .body(novoConvenio.toEntityModel());
    }

    public  ResponseEntity<EntityModel<Convenio>> atualizarConvenio(@PathVariable Long id, @RequestBody Convenio dados) {
        Convenio convenio = repository.findById(id)
                                             .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        BeanUtils.copyProperties(dados, convenio, "id");

         repository.save(convenio);

         return ResponseEntity.ok(convenio.toEntityModel());
    }

    public ResponseEntity<Void> apagarConvenio(Long id) {
        verificarSeExiste(id);
        Convenio apagarConvenio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        apagarConvenio.setAtivo(false);

        return ResponseEntity.noContent().build();

    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
