package com.example.etulas.model.hospital;

import org.hibernate.validator.constraints.br.CNPJ;

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
@Table(name = "T_ETU_HOSPITAL")
public class Hospital {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospital")
    private Long id;
    @Column(name = "nm_nome")
    @NotBlank(message = "{hospital.nome.notblank}")
    @Size(min = 10, max = 60, message = "{hospital.nome.size}")
    private String nome;
    @Column(name = "nr_telefone")
    @Size(min = 11, max = 11, message = "{hospital.telefone.size}")
    @NotBlank(message = "{hospital.telefone.notblank}")
    private String telefone;
    @Column(name = "nr_cnpj", unique = true)
    @CNPJ(message = "{hospital.cnpj.CNPJ}")
    @NotBlank(message = "{hospital.cnpj.notblank}")
    private String cnpj;
    @Column(name = "st_ativo")
    @Pattern(
        regexp = "^(SIM|NÃO)$",
        message = "{hospital.ativo.pattern}"
    )
    @NotBlank(message = "{hospital.ativo.notblank}")
    private String ativo;

    public boolean isAtivo(){
        return ativo == "Sim";
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo ? "Sim" : "Não";
    }

}
