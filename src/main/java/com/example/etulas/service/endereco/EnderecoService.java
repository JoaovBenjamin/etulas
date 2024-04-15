package com.example.etulas.service.endereco;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.endereco.EnderecoDTO;
import com.example.etulas.model.endereco.Endereco;
import com.example.etulas.repository.endereco.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    EnderecoRepository repository;

    public List<Endereco> buscarEndereco(){
        return repository.findAll();
    }

    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public Endereco criarEndereco(EnderecoDTO dados){
        Endereco novoEndereco = new Endereco(dados);
        return repository.save(novoEndereco);

    }

    public Endereco atualizarEndereco(Long id, EnderecoDTO dados){
        verificarSeExiste(id);
        Endereco atualizadoEndereco = new Endereco(dados);
        return repository.save(atualizadoEndereco);
    }

    public void deletarEndereco(Long id){
        verificarSeExiste(id);
        repository.deleteById(id);
    }

    public void verificarSeExiste(Long id){
        repository.
                    findById(id)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
