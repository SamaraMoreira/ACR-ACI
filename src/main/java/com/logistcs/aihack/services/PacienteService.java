package com.logistcs.aihack.services;

import com.logistcs.aihack.domain.entities.Endereco;
import com.logistcs.aihack.domain.entities.Paciente;
import com.logistcs.aihack.dtos.EnderecoCreateDTO;
import com.logistcs.aihack.dtos.PacienteCreateDTO;
import com.logistcs.aihack.dtos.PacienteUpdateDTO;
import com.logistcs.aihack.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PacienteRepository repository;

    @Transactional
    public Paciente newPaciente(PacienteCreateDTO pacienteCreateDTO){
        Endereco endereco = enderecoService.newEndereco(pacienteCreateDTO.getEndereco());
        Paciente paciente = new Paciente(null, pacienteCreateDTO.getTelefone(), pacienteCreateDTO.getNome(),
                pacienteCreateDTO.getDataNascimento(), endereco);

        return repository.save(paciente);
    }

    public List<Paciente> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public PacienteUpdateDTO preparaUpdateDto(Long id) {
        Paciente paciente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado para o id: "+id));
        PacienteUpdateDTO pacienteUpdateDTO = new PacienteUpdateDTO(paciente.getId(),paciente.getTelefone(), paciente.getNome(), new EnderecoCreateDTO(paciente.getEndereco()));
        return pacienteUpdateDTO;
    }

    @Transactional
    public void update(PacienteUpdateDTO pacienteUpdateDTO) {
        Paciente paciente = repository.findById(pacienteUpdateDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado para o id: "+pacienteUpdateDTO.getId()));

        paciente.setNome(pacienteUpdateDTO.getNome());
        paciente.setTelefone(pacienteUpdateDTO.getTelefone());

        Endereco endereco = paciente.getEndereco();
        enderecoService.update(endereco, pacienteUpdateDTO.getEndereco());
    }
}
