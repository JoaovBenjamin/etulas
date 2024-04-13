package com.example.etulas.model.convenio;

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
@Table(name = "T_ETU_CONVENIO")
public class Convenio {
    @Column(name = "id_convenio")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_nome")
    @Size(min = 10, max = 60, message = "{convenio.nome.size}")
    private String nome;
    @Column(name = "tx_cnpj")
    @CNPJ(message = "{convenio.cnpj.CNPJ}")
    @NotBlank(message = "{convenio.cnpj.notblank}")
    private String cnpj;
    @Column(name = "tx_telefone")
    @Size(min = 11, max = 13, message = "{convenio.telefone.size}")
    @NotBlank(message = "{convenio.telefone.notblank}")
    private String telefone;
    @Column(name = "st_ativo")
    @Pattern(
        regexp = "^(SIM|NÃO)$",
        message = "{convenio.ativo.pattern}"
    )
    private String ativo;

    public boolean isAtivo(){
        return ativo == "Sim";
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo ? "Sim" : "Não";
    }
}
