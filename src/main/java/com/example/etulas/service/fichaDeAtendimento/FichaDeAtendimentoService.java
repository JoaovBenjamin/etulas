package com.example.etulas.service.fichaDeAtendimento;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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

    public EntityModel<FichaDeAtendimento> buscarFichaDeAtendimentoPorId(@PathVariable Long id) {
        var fichaAtendimento = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Não encontrado a ficha de atendimento"));

        return fichaAtendimento.toEntityModel();
    }

    public ResponseEntity<EntityModel<FichaDeAtendimento>> criarFichaDeAtendimento(FichaDeAtendimento dados) {
        FichaDeAtendimento novaFicha = dados;
        repository.save(novaFicha);

        return ResponseEntity
                            .created(novaFicha.toEntityModel().getRequiredLink("self").toUri())
                            .body(novaFicha.toEntityModel());
    }

    public ResponseEntity<EntityModel<FichaDeAtendimento>> atualizarFichaDeAtendimento(Long id, FichaDeAtendimento dados) {
        verificarSeExiste(id);
        FichaDeAtendimento atualizadaFicha = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha de atendimento não encontrada"));
        BeanUtils.copyProperties(dados, atualizadaFicha, "id");
        repository.save(atualizadaFicha);

        return ResponseEntity.ok(atualizadaFicha.toEntityModel());
    }

    public ResponseEntity<Void> apagarFichaDeAtendimento(Long id) {
        verificarSeExiste(id);
        FichaDeAtendimento fichaApagar = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha de atendimento não encontrada"));
        fichaApagar.setAtivo(false);

        return ResponseEntity.noContent().build();

    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
