package com.logistcs.aihack.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusConsulta {
    AGENDADA(0),
    CANCELADA(1),
    FINALIZADA(2),
    ANDAMENTO(3);

    private final int value;

    StatusConsulta(int value){
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static StatusConsulta fromValue(int value){
        for (StatusConsulta status : values()){
            if (status.value == value){
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + value);
    }
}
