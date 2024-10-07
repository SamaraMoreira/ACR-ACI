package com.logistcs.aihack.domain.entities;

import com.logistcs.aihack.domain.enums.StatusConsulta;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.ORDINAL)
    private StatusConsulta status;

    private String prescricao;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime dataHora, StatusConsulta status, String prescricao, String observacoes, Paciente paciente, Medico medico) {
        this.id = id;
        this.dataHora = dataHora;
        this.status = status;
        this.prescricao = prescricao;
        this.observacoes = observacoes;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(id, consulta.id) && Objects.equals(dataHora, consulta.dataHora) && status == consulta.status && Objects.equals(prescricao, consulta.prescricao) && Objects.equals(observacoes, consulta.observacoes) && Objects.equals(paciente, consulta.paciente) && Objects.equals(medico, consulta.medico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataHora, status, prescricao, observacoes, paciente, medico);
    }
}
