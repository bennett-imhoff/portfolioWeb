package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistasUsuarioController {

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping("/")
    public String home (Model model){
        Propietario propietario = propietarioService.ver();
        model.addAttribute("propietario", propietario);
        return "home";
    }
}
