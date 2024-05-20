package com.example.etulas.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.etulas.model.anamnesia.Anamnesia;
import com.example.etulas.model.convenio.Convenio;
import com.example.etulas.model.endereco.Endereco;
import com.example.etulas.model.endereco.EnderecoEnum;
import com.example.etulas.model.equipamentosMedicos.Equipamentos;
import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.model.especialidades.EspecialidadesEnum;
import com.example.etulas.model.fichaAtendimento.FichaDeAtendimento;
import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.model.paciente.Paciente;
import com.example.etulas.model.sala.Sala;
import com.example.etulas.repository.anamnesia.AnamnesiaRepository;
import com.example.etulas.repository.convenio.ConvenioRepository;
import com.example.etulas.repository.endereco.EnderecoRepository;
import com.example.etulas.repository.equipamentos.EquipamentosRepository;
import com.example.etulas.repository.especialidade.EspecialidadeRepository;
import com.example.etulas.repository.fichaAtendimento.FichaAtendimentoRepository;
import com.example.etulas.repository.hospital.HospitalRepository;
import com.example.etulas.repository.paciente.PacienteRepository;
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

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    EquipamentosRepository equipamentosRepository;

    @Autowired
    FichaAtendimentoRepository fichaAtendimentoRepository;

    @Override
    public void run(String... args) throws Exception{

        hospitalRepository.saveAll(
          List.of(
            Hospital
                    .builder()
                    .id(1L)
                    .ativo(true)
                    .cnpj("05.388.218/0001-89")
                    .telefone("66554433221")
                    .nome("Hospital Universitario")
                    .build()
          )  
        );

        pacienteRepository.saveAll(
            List.of(
                Paciente
                        .builder()
                        .id(1L)
                        .cpf("699.172.060-78")
                        .genero("FEMININO")
                        .nome("Joana")
                        .hospital(hospitalRepository.findById(1L).get())
                        .idade(22)
                        .telefone("11988889076")
                        .build()
                        
                
                    
            )
        );

        especialidadeRepository.saveAll(
            List.of(
                Especialidades
                             .builder()
                             .id(1L)
                             .nomeEspecialidade(EspecialidadesEnum.CARDIOLOGIA)
                             .descricaoEspecialidade("Especialidade médica que se dedica ao diagnóstico e tratamento de doenças do coração e do sistema cardiovascular.")
                             .descricaoProcedimento("Realização de consultas, exames de imagem, eletrocardiograma, ecocardiograma, entre outros.")
                             .ativo(true)
                             .hospital(hospitalRepository.findById(1L).get())
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
                    .hospital(hospitalRepository.findById(1L).get())
                    .ativo(true)
                    .build()
                    
            )
        );

        anamnesiaRepository.saveAll(
            List.of(
                Anamnesia
                         .builder()
                         .id(1L)
                         .alergias( "Alergia à penicilina")
                         .lesoes("Erupção cutânea na região dos braços e pernas")
                         .genetica("Histórico familiar de diabetes")
                         .cronicas("Hipertensão")
                         .paciente(pacienteRepository.findById(1L).get())
                         .build()
            )
        );        

        convenioRepository.saveAll(

            List.of(
                Convenio
                .builder()
                .id(1L)
                .nome("Convenio A")
                .cnpj("37.050.194/0001-40")
                .telefone("11982004913")
                .ativo(true)
                .paciente(pacienteRepository.findById(1L).get())
                .build()
            )
      
        );

        enderecoRepository.saveAll(
            List.of(
                Endereco
                .builder()
                .id(1L)
                .cidade("Santo André")
                .bairro("Vila Luzita")
                .logadouro("Rua dos Cocais")
                .numeroEdificio("789")
                .hospital(hospitalRepository.findById(1L).get())
                .enderecoEnum(EnderecoEnum.PA)
                .paciente(pacienteRepository.findById(1L).get())
                .build()
            )
        );

        equipamentosRepository.saveAll(
            List.of(
                Equipamentos
                .builder()
                .id(1L)
                .nome("Ressonância Magnética")
                .procedimento("Realização de exames de imagem detalhados para diagnóstico de diversas condições médicas.")
                .sala(7)
                .hospital(hospitalRepository.findById(1L).get())
                .ativo(true)
                .build()
            )
        );

        fichaAtendimentoRepository.saveAll(
            List.of(
                FichaDeAtendimento
                .builder()
                .id(1L)
                .altura(67.9)
                .pressao("120/80")
                .temperatura(35.0)
                .ativo(true)
                .saidaPaciente(LocalDate.now().minusDays(1))
                .peso(78.80)
                .entradaPaciente(LocalDate.now())
                .dores("Dores leves nas costas")
                .especialidades(especialidadeRepository.findById(1L).get())
                .hospital(hospitalRepository.findById(1L).get())
                .paciente(pacienteRepository.findById(1L).get())
                .build()
            )
        );

        hospitalRepository.saveAll(
            List.of(
                Hospital
                .builder()
                .ativo(true)
                .id(1L)
                .cnpj("05.388.218/0001-89")
                .nome("Hospital A")
                .telefone("11982004999")
                .build()
            )
        );


        pacienteRepository.saveAll(
            List.of(
                Paciente
                .builder()
                .id(1L)
                .nome("Joana")
                .cpf("14554884023")
                .telefone("11977779999")
                .idade(25)
                .genero("FEMININO")
                .build()
            )
        );
    }
}
