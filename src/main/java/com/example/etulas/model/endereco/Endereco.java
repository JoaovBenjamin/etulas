package com.example.etulas.model.endereco;

import com.example.etulas.model.hospital.Hospital;
import com.example.etulas.model.paciente.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name = "T_ETU_ENDERECO")
public class Endereco {
    @Column(name = "id_endereco")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nr_edificio")
    @NotBlank(message = "{endereco.numeroEdificio.notblank}")
    private String numeroEdificio;
    @Column(name = "nm_logadouro")
    @NotBlank(message = "{endereco.logadouro.notblank}")
    @Size(min = 4, max = 60, message = "{endereco.logadouro.size}")
    private String logadouro;
    @Column(name = "nm_bairro")
    @NotBlank(message = "{endereco.bairro.notblank}")
    @Size(min = 4, max = 60, message = "{endereco.bairro.size}")
    private String bairro;
    @Column(name = "nm_cidade")
    @NotBlank(message = "{endereco.cidade.notblank}")
    @Size(min = 4, max = 60, message = "{endereco.cidade.size}")
    private String cidade;
    @Column(name = "nm_uf")
    @Enumerated(EnumType.STRING)
    private EnderecoEnum enderecoEnum;
    @NotNull(message = "endereco.hospital.notnull")
    @OneToOne()
    private Hospital hospital;
    @NotNull(message = "endereco.hospital.notnull")
    @OneToOne()
    private Paciente paciente;
    
}
