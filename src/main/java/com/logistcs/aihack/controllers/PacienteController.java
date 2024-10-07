package com.logistcs.aihack.controllers;

import com.logistcs.aihack.domain.enums.EspecialidadeMedico;
import com.logistcs.aihack.dtos.PacienteCreateDTO;
import com.logistcs.aihack.dtos.PacienteUpdateDTO;
import com.logistcs.aihack.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public String getPacientes(Model model){
        model.addAttribute("pacientes",service.findAll());
        return "pacientes-list";
    }

    @GetMapping("/create")
    public String getFormularioDeCriacao(Model model) {
        model.addAttribute("pacienteCreateDTO", new PacienteCreateDTO());
        return "new-paciente";
    }

    @PostMapping
    public String newPaciente(@ModelAttribute @Valid PacienteCreateDTO pacienteCreateDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "new-paciente";
        }
        service.newPaciente(pacienteCreateDTO);
        return "redirect:/pacientes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/pacientes";
    }

    @GetMapping("/edit/{id}")
    public String getFormEdicao(Model model, @PathVariable Long id){
        PacienteUpdateDTO pacienteUpdateDTO = service.preparaUpdateDto(id);
        model.addAttribute("pacienteUpdateDTO", pacienteUpdateDTO);
        return "update-pacientes";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid PacienteUpdateDTO pacienteUpdateDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "update-pacientes";
        }
        service.update(pacienteUpdateDTO);
        return "redirect:/pacientes";
    }
}
