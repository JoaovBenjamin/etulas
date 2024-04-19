package com.example.etulas.service.equipamentos;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.equipamentos.EquipamentosDTO;
import com.example.etulas.model.equipamentosMedicos.Equipamentos;
import com.example.etulas.repository.equipamentos.EquipamentosRepository;

@Service
public class EquipamentosService {

    @Autowired
    EquipamentosRepository repository;

    public Equipamentos salvarEquipamentos(EquipamentosDTO dados) {
        Equipamentos novoEquipamentos = new Equipamentos(dados);
        return repository.save(novoEquipamentos);
    }

    public List<Equipamentos> buscarEquipamentos() {
        return repository.findAll();
    }

    public ResponseEntity<Equipamentos> buscarEquipamentosPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public Equipamentos criarEquipamentos(EquipamentosDTO dados) {
        Equipamentos novoEquipamentos = new Equipamentos(dados);
        return repository.save(novoEquipamentos);
    }

    public Equipamentos atualizarEquipamentos(Long id, EquipamentosDTO dados) {
        verificarSeExiste(id);
        Equipamentos atualizadoEquipamentos = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
        BeanUtils.copyProperties(dados, atualizadoEquipamentos, "id");
        return repository.save(atualizadoEquipamentos);
    }

    public void apagarEquipamentos(Long id) {
        verificarSeExiste(id);
        Equipamentos apagarEquipamentos = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
        apagarEquipamentos.setAtivo(false);
    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
