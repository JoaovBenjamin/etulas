package com.example.etulas.service.convenio;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.convenio.ConvenioDTO;
import com.example.etulas.model.convenio.Convenio;
import com.example.etulas.repository.convenio.ConvenioRepository;

@Service
public class ConvenioService {

    @Autowired
    ConvenioRepository repository;

    public Convenio salvarConvenio(ConvenioDTO dados) {
        Convenio novoConvenio = new Convenio(dados);
        return repository.save(novoConvenio);
    }

    public List<Convenio> buscarConvenio() {
        return repository.findAll();
    }

    public ResponseEntity<Convenio> buscarConvenioPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public Convenio criarConvenio(ConvenioDTO dados) {
        Convenio novoConvenio = new Convenio(dados);
        return repository.save(novoConvenio);
    }

    public Convenio atualizarConvenio(@PathVariable Long id, @RequestBody ConvenioDTO dados) {
        Convenio convenio = repository.findById(id)
                                             .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        BeanUtils.copyProperties(dados, convenio, "id");

        return repository.save(convenio);
    }

    public void apagarConvenio(Long id) {
        verificarSeExiste(id);
        Convenio apagarConvenio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        apagarConvenio.setAtivo(false);

    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
