package com.example.etulas.model.sala;

import com.example.etulas.model.hospital.Hospital;

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
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "T_ETU_SALAS")
public class Sala {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tx_salas")
    @NotBlank(message = "{sala.numerodasala.notblank}")
    private String numeroDaSala;
    @Column(name = "qtd_leitos_uti")
    @NotBlank(message = "{sala.uti.notblank}")
    private String uti;
    @Column(name = "ds_salas")
    @NotBlank(message = "{sala.descricao.notblank}")
    @Size(min = 20, max = 200, message = "{sala.descricao.size}")
    private String descricao;
    @Column(name = "st_ativo")
    private Boolean ativo;
    @NotNull(message = "sala.hospital.notnull")
    @ManyToOne()
    private Hospital hospital;

}


