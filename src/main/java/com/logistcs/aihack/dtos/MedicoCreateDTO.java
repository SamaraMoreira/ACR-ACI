package com.logistcs.aihack.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicoCreateDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String crm;

    @NotNull
    private Integer especialidade;

    @NotBlank
    private String telefone;

    @Valid
    private EnderecoCreateDTO endereco;

    public MedicoCreateDTO() {
    }

    public MedicoCreateDTO(String nome, String crm, Integer especialidade, String telefone, EnderecoCreateDTO endereco) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Integer getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Integer especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoCreateDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCreateDTO endereco) {
        this.endereco = endereco;
    }
}
