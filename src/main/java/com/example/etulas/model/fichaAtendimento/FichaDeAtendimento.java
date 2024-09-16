package com.example.etulas.model.fichaAtendimento;

import java.time.LocalDate;

import com.example.etulas.model.especialidades.Especialidades;
import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.model.paciente.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name = "T_ETU_FICHA_ATENDIMENTO")
public class FichaDeAtendimento {
    @Column(name = "id_atendimento")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nr_peso")
    @NotNull(message = "{fichadeatendimento.peso.notnull}")
    private Double peso;
    @Column(name = "nr_pressao")
    @NotBlank(message = "{fichadeatendimento.pressao.notnull}")
    private String pressao;
    @Column(name = "nr_altura")
    @NotNull(message = "{fichadeatendimento.altura.notnull}")
    private Double altura;
    @Column(name = "nr_temperatura")
    @NotNull(message = "{fichadeatendimento.temperatura.notnull}")
    private Double temperatura;
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
    private Boolean ativo;
    @NotNull(message = "fichaatendimento.especialidade.notnull")
    @ManyToOne()
    private Especialidades especialidades;
    @NotNull(message = "fichaatendimento.paciente.notnull")
    @ManyToOne()
    private Paciente paciente;
    @ManyToOne()
    private Hospital hospital;
   
}
