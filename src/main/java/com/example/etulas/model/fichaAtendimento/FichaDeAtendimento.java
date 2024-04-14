package com.example.etulas.model.fichaAtendimento;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "T_ETU_FICHA_ATENDIMENTO")
public class FichaDeAtendimento {
    @Column(name = "id_atendimento")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nr_peso")
    @NotBlank(message = "{fichadeatendimento.peso.notblank}")
    private Float peso;
    @Column(name = "nr_pressao")
    @NotBlank(message = "{fichadeatendimento.pressao.notblank}")
    private String pressao;
    @Column(name = "nr_altura")
    @NotBlank(message = "{fichadeatendimento.altura.notblank}")
    private Float altura;
    @Column(name = "nr_temperatura")
    @NotBlank(message = "{fichadeatendimento.temperatura.notblank}")
    private Float temperatura;
    @Column(name = "ds_dores")
    @Size(min = 20, max = 200, message = "{fichadeatendimento.dores.size}")
    @NotBlank(message = "{convenio.dores.notblank}")
    private String dores;
    @Column(name = "dt_entrada")
    @PastOrPresent(message = "{fichadeatendimento.entradapaciente.pastorpresent}")
    private LocalDate entradaPaciente;
    @Column(name = "dt_encerramento")
    @PastOrPresent(message = "{fichadeatendimento.saidapaciente.pastorpresent}")
    private LocalDate saidaPaciente;
    @Column(name = "st_ativo")
    @Pattern(
        regexp = "^(SIM|NÃO)$",
        message = "{fichadeatendimento.ativo.pattern}"
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
