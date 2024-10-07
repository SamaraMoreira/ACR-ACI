package com.logistcs.aihack.controllers;

import com.logistcs.aihack.domain.enums.EspecialidadeMedico;
import com.logistcs.aihack.dtos.MedicoCreateDTO;
import com.logistcs.aihack.dtos.MedicoUpdateDTO;
import com.logistcs.aihack.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @GetMapping
    public String getMedicos(Model model){
        model.addAttribute("medicos",service.findAll());
        return "medicos-list";
    }

    @GetMapping("/create")
    public String getFormCriacao(Model model){
        model.addAttribute("especialidades", EspecialidadeMedico.values());
        model.addAttribute("medicoCreateDTO", new MedicoCreateDTO());
        return "new-medico";
    }

    @PostMapping
    public String newMedico(@ModelAttribute @Valid MedicoCreateDTO medicoCreateDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("especialidades", EspecialidadeMedico.values());
            return "new-medico";
        }
        service.newMedico(medicoCreateDTO);
        return "redirect:/medicos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/medicos";
    }

    @GetMapping("/edit/{id}")
    public String getFormEdicao(Model model, @PathVariable Long id){
        MedicoUpdateDTO medicoUpdateDTO = service.preparaUpdateDto(id);
        model.addAttribute("medicoUpdateDTO", medicoUpdateDTO);
        model.addAttribute("especialidades",EspecialidadeMedico.values());
        return "update-medico";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid MedicoUpdateDTO medicoUpdateDTO,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("especialidades", EspecialidadeMedico.values());
            return "update-medico";
        }
        service.update(medicoUpdateDTO);
        return "redirect:/medicos";
    }
}
