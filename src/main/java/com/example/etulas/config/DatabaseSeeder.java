package com.example.etulas.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.etulas.model.anamnesia.Anamnesia;
import com.example.etulas.model.convenio.Convenio;
import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.model.especialidades.EspecialidadesEnum;
import com.example.etulas.model.sala.Sala;
import com.example.etulas.repository.anamnesia.AnamnesiaRepository;
import com.example.etulas.repository.convenio.ConvenioRepository;
import com.example.etulas.repository.especialidade.EspecialidadeRepository;
import com.example.etulas.repository.sala.SalaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    EspecialidadeRepository especialidadeRepository;

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    AnamnesiaRepository anamnesiaRepository;

    @Autowired
    ConvenioRepository convenioRepository;

    @Override
    public void run(String... args) throws Exception{
        especialidadeRepository.saveAll(
            List.of(
                Especialidades
                             .builder()
                             .id(1L)
                             .nomeEspecialidade(EspecialidadesEnum.CARDIOLOGIA)
                             .descricaoEspecialidade("Especialidade médica que se dedica ao diagnóstico e tratamento de doenças do coração e do sistema cardiovascular.")
                             .descricaoProcedimento("Realização de consultas, exames de imagem, eletrocardiograma, ecocardiograma, entre outros.")
                             .ativo(true)
                             .build()
            )
        );

        salaRepository.saveAll(
            List.of(
                Sala
                    .builder()
                    .id(1L)
                    .numeroDaSala("12")
                    .descricao("Sala de UTI com 10 leitos")
                    .uti("Sim")
                    .ativo(true)
                    .build()
                    
            )
        );

        anamnesiaRepository.saveAll(
            List.of(
                Anamnesia
                         .builder()
                         .alergias( "Alergia à penicilina")
                         .lesoes("Erupção cutânea na região dos braços e pernas")
                         .genetica("Histórico familiar de diabetes")
                         .cronicas("Hipertensão")
                         .build()
            )
        );        

        convenioRepository.saveAll(

            List.of(
                Convenio
                .builder()
                .nome("Convenio A")
                .cnpj("37.050.194/0001-40")
                .telefone("11982004913")
                .ativo(true)
                .build()
            )
      
        );

    }
}
