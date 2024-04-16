package com.example.etulas.model.sala;

import com.example.etulas.dto.sala.SalaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
    @NotBlank(message = "{sala.descricao.notblank}")
    @Size(min = 20, max = 200, message = "{sala.descricao.size}")
    private String descricao;
    @Column(name = "st_ativo")
    private Boolean ativo;

    public Sala(SalaDTO dados){
        this.ativo=dados.ativo();
        this.descricao = dados.descricao();
        this.numeroDaSala = dados.numeroDaSala();
        this.uti = dados.uti();
    }
}


