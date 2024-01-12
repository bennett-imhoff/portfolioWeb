package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.domain.RedSocial;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import com.benimhoff.portfolioWeb.service.RedSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VistasUsuarioController {

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    private RedSocialService redSocialService;

    @GetMapping("/")
    public String home (Model model){
        Propietario propietario = propietarioService.ver();
        List<RedSocial> redesSociales = redSocialService.verTodas();
        model.addAttribute("propietario", propietario);
        model.addAttribute("redesSociales", redesSociales);
        return "home";
    }
}
