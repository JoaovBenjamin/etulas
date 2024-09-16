package com.example.etulas.service.equipamentos;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.equipamentosMedicos.Equipamentos;
import com.example.etulas.repository.equipamentos.EquipamentosRepository;

@Service
public class EquipamentosService {

    @Autowired
    EquipamentosRepository repository;

    public Equipamentos salvarEquipamentos(Equipamentos dados) {
        Equipamentos novoEquipamentos = dados;
        return repository.save(novoEquipamentos);
    }

    public List<Equipamentos> buscarEquipamentos() {
        return repository.findAll();
    }

    public EntityModel<Equipamentos> buscarEquipamentosPorId(@PathVariable Long id) {
         var equipamentos = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));

         return equipamentos.toEntityModel();
    }

    public ResponseEntity<EntityModel<Equipamentos>> criarEquipamentos(Equipamentos dados) {
        Equipamentos novoEquipamentos = dados;
        repository.save(novoEquipamentos);

        return ResponseEntity
                      .created(novoEquipamentos.toEntityModel().getRequiredLink("self").toUri())
                      .body(novoEquipamentos.toEntityModel());
    }

    public ResponseEntity<EntityModel<Equipamentos>> atualizarEquipamentos(Long id, Equipamentos dados) {
        verificarSeExiste(id);
        Equipamentos atualizadoEquipamentos = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
        BeanUtils.copyProperties(dados, atualizadoEquipamentos, "id");
         repository.save(atualizadoEquipamentos);

         return ResponseEntity.ok(atualizadoEquipamentos.toEntityModel());
    }

    public ResponseEntity<Void> apagarEquipamentos(Long id) {
        verificarSeExiste(id);
        Equipamentos apagarEquipamentos = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
        apagarEquipamentos.setAtivo(false);

        return ResponseEntity.noContent().build();
    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
