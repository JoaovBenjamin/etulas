package com.example.etulas.model.especialidades;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

import com.example.etulas.controller.especialidades.EspecialidadesController;
import com.example.etulas.model.hospital.Hospital;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "T_ETU_ESPECIALIDADES")
public class Especialidades {
    @Column(name = "id_especialidade")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_especialidade")
    @Enumerated(EnumType.STRING)
    private EspecialidadesEnum nome;
    @Column(name = "ds_especialidade")
    @NotBlank(message = "{especialidades.descricaoespecialidade.notblank}")
    @Size(min = 50, max = 200, message = "{especialidades.descricaoespecialidade.size}")
    private String descricaoEspecialidade;
    @Column(name = "ds_procedimento")
    @NotBlank(message = "{especialidades.descricaoprocedimento.notblank}")
    @Size(min = 50, max = 200, message = "{especialidades.descricaoprocedimento.size}")
    private String descricaoProcedimento;
    @Column(name = "st_ativo")
    private Boolean ativo;
    @NotNull(message = "{especialidades.hospital.notnull}")
    @ManyToOne()
    private Hospital hospital;


    public EntityModel<Especialidades> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(EspecialidadesController.class).buscarEspecialidadePorId(id)).withSelfRel(),
            linkTo(methodOn(EspecialidadesController.class).deletarEspecialidade(id)).withRel("delete")
        );
    }
   
}
