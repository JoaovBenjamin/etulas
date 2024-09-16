package com.example.etulas.model.hospital;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.hateoas.EntityModel;

import com.example.etulas.controller.hospital.HospitalController;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ETU_HOSPITAL")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_nome")
    @NotBlank(message = "{hospital.nome.notblank}")
    @Size(min = 5, max = 60, message = "{hospital.nome.size}")
    private String nome;
    @Column(name = "tx_telefone")
    @Size(min = 11, max = 11, message = "{hospital.telefone.size}")
    @NotBlank(message = "{hospital.telefone.notblank}")
    private String telefone;
    @Column(name = "tx_cnpj", unique = true)
    @CNPJ(message = "{hospital.cnpj.CNPJ}")
    @NotBlank(message = "{hospital.cnpj.notblank}")
    private String cnpj;
    @Column(name = "st_ativo")
    private Boolean ativo;
   

    public EntityModel<Hospital> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(HospitalController.class).buscarHospitalPorId(id)).withSelfRel(),
            linkTo(methodOn(HospitalController.class).index(null, null)).withRel("contents"),
            linkTo(methodOn(HospitalController.class).deletarHospital(id)).withRel("delete")
        );
    }

}
