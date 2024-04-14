package com.example.etulas.model.equipamentosMedicos;

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

@Data
@Entity
@Table(name = "T_ETU_EQUIPAMENTOS_MEDICOS")
public class Equipamentos {
    @Column(name = "id_equipamentos")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nm_equipamento")
    @NotBlank(message = "{equipamentos.nome.notblank}")
    @Size(message = "{equipamentos.nome.size}", min = 10, max = 60)
    private String nome;
    @Column(name = "ds_procedimento")
    @NotBlank(message = "{equipamentos.procedimento.notblank}")
    @Size(message = "{equipamentos.procedimento.size}", min = 20, max = 200)
    private String procedimento;
    @Column(name = "nr_sala")
    @NotBlank(message = "{equipamentos.sala.notblank}")
    private int sala;
    @Column(name = "st_ativo")
    @Pattern(
        regexp = "^(SIM|NÃO)$",
        message = "{especialidade.ativo.pattern}"
    )
    private String ativo;

    public boolean isAtivo(){
        return ativo == "Sim";
    }
}
