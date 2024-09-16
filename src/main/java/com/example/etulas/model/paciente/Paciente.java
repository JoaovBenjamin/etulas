package com.example.etulas.model.paciente;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


import com.example.etulas.controller.paciente.PacienteController;
import com.example.etulas.model.hospital.Hospital;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@Table(name = "T_ETU_PACIENTE")
public class Paciente {
    @Column(name = "id_paciente")  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_paciente", unique = true)
    @NotBlank(message = "{paciente.nome.notblank}")
    @Size(min = 5, max = 60, message = "{paciente.nome.size}")
    private String nome;
    @Column(name = "tx_cpf", unique = true)
    @CPF(message = "{paciente.cpf.CPF}")
    @NotBlank(message = "{paciente.cpf.notblank}")
    private String cpf;
    @Column(name = "tx_telefone", unique = true)
    @NotBlank(message = "{paciente.telefone.notblank}")
    @Size(min = 11, max = 13, message = "{paciente.telefone.size}")
    private String telefone;
    @Column(name = "nr_idade")
    @NotNull(message = "{paciente.idade.notnull}")
    private int idade;
    @Column(name = "ds_genero")
    @Pattern(regexp = "^(MASCULINO|FEMININO)$", message = "{paciente.genero.pattern}")
    private String genero;
   
    public EntityModel<Paciente> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(PacienteController.class).buscarPacientePorId(id)).withSelfRel(),
            linkTo(methodOn(PacienteController.class).buscarPacientePorCpf(cpf)).withRel(cpf),
            linkTo(methodOn(PacienteController.class).index(null, null, null)).withRel("contents"),
            linkTo(methodOn(PacienteController.class).deletarPaciente(id)).withRel("delete")
        );
    }

   
}
