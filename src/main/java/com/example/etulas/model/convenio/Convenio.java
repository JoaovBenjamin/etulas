package com.example.etulas.model.convenio;

import org.hibernate.validator.constraints.br.CNPJ;

import com.example.etulas.dto.convenio.ConvenioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "T_ETU_CONVENIO")
public class Convenio {
    @Column(name = "id_convenio")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_nome")
    @NotBlank(message = "{convenio.nome.notblank}")
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
    private Boolean ativo;

    public Convenio(ConvenioDTO dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cnpj = dados.cnpj();
        this.ativo = dados.ativo();
    }
}
