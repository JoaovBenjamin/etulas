package com.example.etulas.service.Paciente;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.repository.paciente.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository repository;

      @Autowired
    PagedResourcesAssembler<Paciente> pageAssembler;

    public Paciente salvarPaciente(Paciente dados) {
        Paciente novoPaciente = dados;
        return repository.save(novoPaciente);
    }

    public List<Paciente> buscarPaciente() {
        return repository.findAll();
    }

    public PagedModel<EntityModel<Paciente>> index(
        @RequestParam(required = false) String paciente,
        @RequestParam(required = false) String cpf,
        @PageableDefault(size = 5, direction = Direction.DESC) Pageable pageable
    ){

        Page<Paciente> page = null;

        if(paciente !=null && cpf !=null){
            page = repository.findByNomeAndCpf(paciente,cpf, pageable);
        }

        if(paciente !=null){
            page = repository.findByNome(paciente, pageable);
        }

        if (cpf != null) {
            page = repository.findByCpf(cpf, pageable);
        }
    
        if(page == null){
            page = repository.findAll(pageable);
        }
        
        return pageAssembler.toModel(page, Paciente::toEntityModel);
    }


    public EntityModel<Paciente> buscarPacientePorId(@PathVariable Long id) {
            var paciente = repository.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

            return paciente.toEntityModel();

    }

        public ResponseEntity<EntityModel<Paciente>> buscarPacientePorCpf(@PathVariable String cpf) {
            var paciente = repository.findByCpf(cpf);

               if (paciente == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(paciente.toEntityModel());
            
        }

    public ResponseEntity<EntityModel<Paciente>> criarPaciente(Paciente dados) {
        Paciente novoPaciente = dados;
         repository.save(novoPaciente);

         return ResponseEntity
         .created(novoPaciente.toEntityModel().getRequiredLink("self").toUri())
         .body(novoPaciente.toEntityModel());
    }

    public ResponseEntity<EntityModel<Paciente>> atualizarPaciente(Long id, Paciente dados) {
        verificarSeExiste(id);
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        BeanUtils.copyProperties(dados, paciente, "id");
        repository.save(paciente);
        return ResponseEntity.ok(paciente.toEntityModel());
    }

    public ResponseEntity<Void> deletarPaciente(Long id) {
        verificarSeExiste(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
