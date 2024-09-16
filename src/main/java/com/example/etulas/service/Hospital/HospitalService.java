package com.example.etulas.service.Hospital;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.repository.hospital.HospitalRepository;

@Service
public class HospitalService {

    @Autowired
    HospitalRepository repository;

    @Autowired
    PagedResourcesAssembler<Hospital> pageAssembler;

    public Hospital salvarHospital(Hospital dados) {
        Hospital novoHospital = dados;
        return repository.save(novoHospital);
    }
    

    public List<Hospital> buscarHospital() {
        return repository.findAll();
    }

     public PagedModel<EntityModel<Hospital>> index(
        @RequestParam(required = false) String hospital,
        @PageableDefault(size = 5, direction = Direction.DESC) Pageable pageable
    ){
        Page<Hospital> page = null;

        if (hospital !=null){
            page = repository.findByNome(hospital, pageable);
        }
    

        if(page == null){
            page = repository.findAll(pageable);
        }

       return pageAssembler.toModel(page,Hospital::toEntityModel);
    
    }

    public EntityModel<Hospital> buscarHospitalPorId(@PathVariable Long id) {
        var hospital = repository.findById(id)
                                  .orElseThrow(() -> new IllegalArgumentException("Hospital não encontrado"));

        return hospital.toEntityModel();
    }

    public ResponseEntity<EntityModel<Hospital>> criarHospital(Hospital dados) {
        Hospital novoHospital = dados;
        repository.save(novoHospital);

        return ResponseEntity
                            .created(novoHospital.toEntityModel().getRequiredLink("self").toUri())
                            .body(novoHospital.toEntityModel());
    }

    public ResponseEntity<EntityModel<Hospital>> atualizarHospital(Long id, Hospital dados) {
        Hospital hospital = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        BeanUtils.copyProperties(dados, hospital, "id");
        repository.save(hospital);

        return ResponseEntity.ok(hospital.toEntityModel());
    }
      


    public ResponseEntity<Void> deletarHospital(Long id) {
        verificarSeExiste(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void verificarSeExiste(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }

}