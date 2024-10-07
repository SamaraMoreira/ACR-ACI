package com.logistcs.aihack.domain.enums;

public enum EspecialidadeMedico {
    CARDIOLOGISTA(0),
    ORTOPEDISTA(1);

    private Integer value;

    EspecialidadeMedico(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

    public static EspecialidadeMedico especialidadeMedico(Integer value){
        for (EspecialidadeMedico especialidade : values()){
            if (especialidade.getValue().equals(value)){
                return especialidade;
            }
        }
        throw new IllegalArgumentException("Especialidade inválida: " + value);
    }
}
