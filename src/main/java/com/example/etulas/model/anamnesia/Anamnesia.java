package com.example.etulas.model.anamnesia;

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
@Table(name = "T_ETU_ANAMNESIA")
public class Anamnesia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ds_lesoes")
    @Size(min = 10, max = 200, message = "{anamnesia.lesoes.size}")
    @NotBlank(message = "{anamnesia.lesoes.notblank}")
    private String lesoes;
    @Column(name = "ds_doencas_geneticas")
    @Size(min = 10, max = 200, message = "{anamnesia.genetica.size}")
    @NotBlank(message = "{anamnesia.genetica.notblank}")
    private String genetica;
    @Column(name = "ds_doencas_cronicas")
    @Size(min = 10, max = 200, message = "{anamnesia.cronicas.size}")
    @NotBlank(message = "{anamnesia.lesoes.notblank}")
    private String cronicas;
    @Column(name = "ds_alergias")
    @Size(min = 10, max = 200, message = "{anamnesia.alergias.size}")
    @NotBlank(message = "{anamnesia.alergias.notblank}")
    private String alergias;
}
