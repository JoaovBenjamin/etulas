package com.example.etulas.model.especialidades;

public enum EspecialidadesEnum {
    CARDIOLOGIA,
    NEUROLOGIA,
    PEDIATRIA,
    NEFROLOGIA,
    RADIOLOGIA,
    OFTALMOLOGIA,
    ONCOLOGIA;

    public String nomeEspecialidade() {
        switch (this) {
            case CARDIOLOGIA:
                return "Cardiologia";
            case ONCOLOGIA:
                return "Oncologia";
            case PEDIATRIA:
                return "Pediatria";
            case NEFROLOGIA:
                return "Nefrologia";
            case NEUROLOGIA:
                return "Neurologia";
            case OFTALMOLOGIA:
                return "Oftalmologia";
            case RADIOLOGIA:
                return "Radiologia";
            default:
                return "Especialidade desconhecida";
        }
    }
}