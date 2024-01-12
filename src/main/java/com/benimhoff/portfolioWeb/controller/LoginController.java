package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.domain.RedSocial;
import com.benimhoff.portfolioWeb.exception.LoginException;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import com.benimhoff.portfolioWeb.service.RedSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    private RedSocialService redSocialService;

    private boolean usuarioAutenticado = false;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String ingresar(@RequestParam("username") String username, @RequestParam("password") String password) throws LoginException {
        usuarioAutenticado = propietarioService.esUsuarioValido(username, password);
        return "redirect:/homeEdicion";
    }

    @GetMapping("/homeEdicion")
    public String homeEdicion(Model model){
        Propietario propietario = propietarioService.ver();
        List<RedSocial> redesSociales = redSocialService.verTodas();
        model.addAttribute("propietario", propietario);
        model.addAttribute("redesSociales", redesSociales);
        return usuarioAutenticado ? "homeEdicion" : "redirect:/login";
    }
}
