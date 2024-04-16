package com.example.etulas.service.especialidades;

import java.util.List;
import static org.springframework.http.HttpStatus.NOT_FOUND;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.especialidades.EspecialidadesDTO;
import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.repository.especialidade.EspecialidadeRepository;

@Service
public class EspecialidadesService {
    @Autowired
EspecialidadeRepository repository;

public Especialidades salvarEspecialidade(EspecialidadesDTO dados) {
    Especialidades novaEspecialidade = new Especialidades(dados);
    return repository.save(novaEspecialidade);
}

public List<Especialidades> buscarEspecialidades() {
    return repository.findAll();
}

public ResponseEntity<Especialidades> buscarEspecialidadePorId(@PathVariable Long id) {
    return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

public Especialidades criarEspecialidade(EspecialidadesDTO dados) {
    Especialidades novaEspecialidade = new Especialidades(dados);
    return repository.save(novaEspecialidade);
}

public Especialidades atualizarEspecialidade(Long id) {
    verificarSeExiste(id);
    Especialidades especialidadeAtualizada = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
    especialidadeAtualizada.setId(id);
    return repository.save(especialidadeAtualizada);
}

public void apagarEspecialidade(Long id) {
    verificarSeExiste(id);
    Especialidades especialidadeApagar = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
    especialidadeApagar.setAtivo(false);
}

public void verificarSeExiste(Long id) {
    repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
}

}
