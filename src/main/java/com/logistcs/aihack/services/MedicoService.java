package com.logistcs.aihack.services;

import com.logistcs.aihack.domain.entities.Endereco;
import com.logistcs.aihack.domain.entities.Medico;
import com.logistcs.aihack.domain.enums.EspecialidadeMedico;
import com.logistcs.aihack.dtos.EnderecoCreateDTO;
import com.logistcs.aihack.dtos.MedicoCreateDTO;
import com.logistcs.aihack.dtos.MedicoUpdateDTO;
import com.logistcs.aihack.repositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private EnderecoService enderecoService;


    @Transactional
    public Medico newMedico(MedicoCreateDTO medicoCreateDTO){
        Endereco endereco = enderecoService.newEndereco(medicoCreateDTO.getEndereco());
        Medico medico = new Medico(null, medicoCreateDTO.getNome(), medicoCreateDTO.getCrm(), EspecialidadeMedico.especialidadeMedico(medicoCreateDTO.getEspecialidade()),
                medicoCreateDTO.getTelefone(), endereco);

        return repository.save(medico);
    }

    public List<Medico> findAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public MedicoUpdateDTO preparaUpdateDto(Long id) {
        Medico medico = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Médico não encontrado para o id: "+id));
        MedicoUpdateDTO medicoUpdateDTO = new MedicoUpdateDTO(medico.getId(),medico.getNome(), medico.getEspecialidade().getValue(), medico.getTelefone(), new EnderecoCreateDTO(medico.getEndereco()));
        return medicoUpdateDTO;
    }

    @Transactional
    public void update(MedicoUpdateDTO medicoUpdateDTO) {
        Medico medico = repository.findById(medicoUpdateDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado para o id: "+medicoUpdateDTO.getId()));

        medico.setNome(medicoUpdateDTO.getNome());
        medico.setEspecialidade(EspecialidadeMedico.especialidadeMedico(medicoUpdateDTO.getEspecialidade()));
        medico.setTelefone(medicoUpdateDTO.getTelefone());

        Endereco endereco = medico.getEndereco();
        enderecoService.update(endereco, medicoUpdateDTO.getEndereco());
    }
}
