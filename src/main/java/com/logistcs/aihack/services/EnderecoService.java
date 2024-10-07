package com.logistcs.aihack.services;

import com.logistcs.aihack.domain.entities.Endereco;
import com.logistcs.aihack.dtos.EnderecoCreateDTO;
import com.logistcs.aihack.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco newEndereco(EnderecoCreateDTO enderecoCreateDTO){
        return repository.save(new Endereco(enderecoCreateDTO));
    }

    public void update(Endereco endereco, EnderecoCreateDTO enderecoDto) {
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setRua(enderecoDto.getRua());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(enderecoDto.getEstado());
        endereco.setCep(enderecoDto.getCep());
        endereco.setComplemento(enderecoDto.getComplemento());
        repository.save(endereco);
    }
}
