package com.example.etulas.model.convenio;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.hateoas.EntityModel;

import com.example.etulas.controller.convenio.ConvenioController;
import com.example.etulas.model.paciente.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ETU_CONVENIO")
public class Convenio {
    @Column(name = "id_convenio")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_nome")
    @NotBlank(message = "{convenio.nome.notblank}")
    @Size(min = 10, max = 60, message = "{convenio.nome.size}")
    private String nome;
    @Column(name = "tx_cnpj", unique = true)
    @CNPJ(message = "{convenio.cnpj.CNPJ}")
    @NotBlank(message = "{convenio.cnpj.notblank}")
    private String cnpj;
    @Column(name = "tx_telefone")
    @Size(min = 11, max = 13, message = "{convenio.telefone.size}")
    @NotBlank(message = "{convenio.telefone.notblank}")
    private String telefone;
    @Column(name = "st_ativo")
    private Boolean ativo;
    @NotNull(message = "convenio.paciente.notnull")
    @ManyToOne()
    private Paciente paciente;


    public EntityModel<Convenio> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(ConvenioController.class).buscarConvenioPorId(id)).withSelfRel(),
            linkTo(methodOn(ConvenioController.class).deletarEndereco(id)).withRel("delete")
        );
    }
 
}
    