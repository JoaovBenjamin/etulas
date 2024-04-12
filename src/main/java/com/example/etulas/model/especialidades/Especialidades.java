package com.example.etulas.model.especialidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "T_ETU_ESPECIALIDADES")
public class Especialidades {
    @Column(name = "id_especialidade")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // TODO: CRIAR UM VALIDATOR PARA ESPECIALIDADES
    @Column(name = "nm_especialidade")
    @Enumerated(EnumType.STRING)
    private EspecialidadesEnum nomeEspecialidade;
    @Column(name = "ds_especialidade")
    @Size(min = 50, max = 200, message = "{especialidades.descricaoespecialidade.size}")
    private String descricaoEspecialidade;
    @Column(name = "ds_procedimento")
    @Size(min = 50, max = 200, message = "{especialidades.descricaoprocedimento.size}")
    private String descricaoProcedimento;
    @Column(name = "st_ativo")
    @Pattern(
        regexp = "^(SIM|NÃO)$",
        message = "{especialidade.ativo.pattern}"
    )
    private String ativo;

    public boolean isAtivo(){
        return ativo == "Sim";
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo ? "Sim" : "Não";
    }
}
