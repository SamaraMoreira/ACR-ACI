package com.logistcs.aihack.controllers;

import com.logistcs.aihack.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("consultas", repository.findAll());
        return "consultas";
    }
}
