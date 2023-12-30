package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    PropietarioService propietarioService;

    private boolean usuarioAutenticado = false;

    @GetMapping("/login")
    public String verLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String ingresar(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
       try {
           propietarioService.esUsuarioValido(username, password);
           usuarioAutenticado = true;
           return "redirect:/homeEdicion";
       } catch (IllegalArgumentException e){
           model.addAttribute("error", e.getMessage());
           return "login";
       }
    }

    @GetMapping("/homeEdicion")
    public String verHomeEdicion(Model model){
        Propietario propietario = propietarioService.ver();
        model.addAttribute("propietario", propietario);
        return usuarioAutenticado ? "homeEdicion" : "redirect:/login";
    }
}
