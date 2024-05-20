package com.example.etulas.model.convenio;

import org.hibernate.validator.constraints.br.CNPJ;

import com.example.etulas.dto.convenio.ConvenioDTO;
import com.example.etulas.model.paciente.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    @OneToOne()
    private Paciente paciente;

    public Convenio(ConvenioDTO dados) {
        this.id = dados.Id();
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cnpj = dados.cnpj();
        this.ativo = dados.ativo();
        this.paciente = dados.paciente();
    }
}
    