package com.example.etulas.service.Hospital;


import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.etulas.dto.hospital.HospitalDTO;
import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.repository.hospital.HospitalRepository;

@Service
public class HospitalService {

    @Autowired HospitalRepository repository;

    public List<Hospital> buscarHospital(){
        return repository.findAll();
    }

    public ResponseEntity<Hospital> buscarHospitalPorId(@PathVariable Long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public Hospital criarHospital(HospitalDTO dados){
        Hospital novoHospital = new Hospital(dados);
        return repository.save(novoHospital);

    }

    public Hospital atualizarHospital(Long id, HospitalDTO dados){
        verificarSeExiste(id);
        Hospital atualizadoHospital = new Hospital(dados);
        return repository.save(atualizadoHospital);
    }

    public void deletarHospital(Long id){
        verificarSeExiste(id);
        repository.deleteById(id);
    }

    public void verificarSeExiste(Long id){
        repository.
                    findById(id)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe id informado"));
    }
}
