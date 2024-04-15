package com.example.etulas.model.paciente;

import org.hibernate.validator.constraints.br.CPF;

import com.example.etulas.dto.paciente.PacienteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "T_ETU_PACIENTE")
public class Paciente {
    @Column(name = "id_paciente")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_paciente", unique = true)
    @NotBlank(message = "{paciente.nome.notblank}")
    @Size(min = 10, max = 60, message = "{paciente.nome.size}")
    private String nome;
    @Column(name = "tx_cpf", unique = true)
    @CPF(message = "{paciente.cpf.CPF}")
    @NotBlank(message = "{paciente.cpf.notblank}")
    private String cpf;
    @Column(name = "tx_telefone", unique = true)
    @NotBlank(message = "{paciente.telefone.notblank}")
    @Size(min = 11,max = 11, message = "{paciente.telefone.size}")
    private String telefone;
    // TODO CRIAR UM VALIDATOR PARA A IDADE
    @Column(name = "nr_idade")
    @NotBlank(message = "{paciente.idade.notblank}")
    private int idade;
    @Column(name = "ds_genero")
    @Pattern(
        regexp = "^(MASCULINO|FEMININO)$",
        message = "{paciente.genero.pattern}"
    )
    private String genero;

    public Paciente(PacienteDTO dados){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.idade = dados.idade();
        this.genero = dados.genero();   
    }
}
