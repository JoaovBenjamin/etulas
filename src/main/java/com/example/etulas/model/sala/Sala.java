package com.example.etulas.model.sala;

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
@Table(name = "T_ETU_SALAS")
public class Sala {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO: Alterar numeroDaSala para String no Banco de dados;
    @Column(name = "nr_salas")
    @NotBlank(message = "{sala.numerodasala.notblank}")
    private String numeroDaSala;
    @Column(name = "qtd_leitos_uti")
    //TODO alterar no banco de dados uti para 
    @NotBlank(message = "{sala.uti.notblank}")
    private String uti;
    @Column(name = "ds_salas")
    @Size(min = 20, max = 200, message = "{sala.descricao.size}")
    private String descricao;
    @Column(name = "st_ativo")
    @Pattern(
        regexp = "^(SIM|NÃO)$",
        message = "{especialidade.ativo.pattern}"
    )
    @NotBlank(message = "{sala.ativo.notblank}")
    private String ativo;

    public boolean isAtivo(){
        return ativo == "Sim";
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo ? "Sim" : "Não";
    }
}
