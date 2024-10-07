package com.logistcs.aihack.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PacienteCreateDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @Valid
    private EnderecoCreateDTO endereco;

    public PacienteCreateDTO() {
    }

    public PacienteCreateDTO(String nome, String telefone, LocalDate dataNascimento, EnderecoCreateDTO endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EnderecoCreateDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCreateDTO endereco) {
        this.endereco = endereco;
    }
}
