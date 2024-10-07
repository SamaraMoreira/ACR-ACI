package com.logistcs.aihack.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PacienteUpdateDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String telefone;

    @NotBlank
    private String nome;

    @Valid
    private EnderecoCreateDTO endereco;

    public PacienteUpdateDTO() {
    }

    public PacienteUpdateDTO(Long id, String telefone, String nome, EnderecoCreateDTO endereco) {
        this.id = id;
        this.telefone = telefone;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoCreateDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCreateDTO endereco) {
        this.endereco = endereco;
    }
}
