package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.exception.LoginExcepcion;
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
    private PropietarioService propietarioService;

    private boolean usuarioAutenticado = false;

    @GetMapping("/login")
    public String verLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String ingresar(@RequestParam("username") String username, @RequestParam("password") String password) throws LoginExcepcion {
        usuarioAutenticado = propietarioService.esUsuarioValido(username, password);
        return "redirect:/homeEdicion";
    }

    @GetMapping("/homeEdicion")
    public String verHomeEdicion(Model model){
        Propietario propietario = propietarioService.ver();
        model.addAttribute("propietario", propietario);
        return usuarioAutenticado ? "homeEdicion" : "redirect:/login";
    }
}
