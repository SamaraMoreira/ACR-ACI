package com.logistcs.aihack.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicoUpdateDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private Integer especialidade;

    @NotBlank
    private String telefone;

    @Valid
    private EnderecoCreateDTO endereco;

    public MedicoUpdateDTO() {
    }

    public MedicoUpdateDTO(Long id,String nome, Integer especialidade, String telefone, EnderecoCreateDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
