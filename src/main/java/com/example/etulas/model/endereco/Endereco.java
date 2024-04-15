package com.example.etulas.model.endereco;

import com.example.etulas.dto.endereco.EnderecoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "T_ETU_ENDERECO")
public class Endereco {
    @Column(name = "id_endereco")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    // TODO FAZER O VALIDATOR DO UF
    @Column(name = "nm_uf")
    @Enumerated(EnumType.STRING)
    private EnderecoEnum enderecoEnum;

    public Endereco(EnderecoDTO dados) {
        this.numeroEdificio = dados.numeroEdificio();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.enderecoEnum = dados.enderecoEnum();
        this.logadouro = dados.logadouro();
    }
}
