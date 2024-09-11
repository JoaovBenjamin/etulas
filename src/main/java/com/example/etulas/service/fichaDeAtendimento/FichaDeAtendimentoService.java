package com.example.etulas.service.fichaDeAtendimento;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.fichaAtendimento.FichaDeAtendimento;
import com.example.etulas.repository.fichaAtendimento.FichaAtendimentoRepository;

@Service
public class FichaDeAtendimentoService {

    @Autowired
    FichaAtendimentoRepository repository;

    public FichaDeAtendimento salvarFichaDeAtendimento(FichaDeAtendimento dados) {
        FichaDeAtendimento novaFicha = dados;
        return repository.save(novaFicha);
    }

    public List<FichaDeAtendimento> buscarFichasDeAtendimento() {
        return repository.findAll();
    }

    public ResponseEntity<FichaDeAtendimento> buscarFichaDeAtendimentoPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public FichaDeAtendimento criarFichaDeAtendimento(FichaDeAtendimento dados) {
        FichaDeAtendimento novaFicha = dados;
        return repository.save(novaFicha);
    }

    public FichaDeAtendimento atualizarFichaDeAtendimento(Long id, FichaDeAtendimento dados) {
        verificarSeExiste(id);
        FichaDeAtendimento atualizadaFicha = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha de atendimento não encontrada"));
        BeanUtils.copyProperties(dados, atualizadaFicha, "id");
        return repository.save(atualizadaFicha);
    }

    public void apagarFichaDeAtendimento(Long id) {
        verificarSeExiste(id);
        FichaDeAtendimento fichaApagar = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha de atendimento não encontrada"));
        fichaApagar.setAtivo(false);

    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
