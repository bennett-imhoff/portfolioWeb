package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.domain.Proyecto;
import com.benimhoff.portfolioWeb.domain.RedSocial;
import com.benimhoff.portfolioWeb.domain.Servicio;
import com.benimhoff.portfolioWeb.exception.LoginException;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import com.benimhoff.portfolioWeb.service.ProyectoService;
import com.benimhoff.portfolioWeb.service.RedSocialService;
import com.benimhoff.portfolioWeb.service.ServicioService;
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

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ProyectoService proyectoService;

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
        List<Servicio> servicios = servicioService.verTodos();
        List<Proyecto> proyectos = proyectoService.verTodos();
        model.addAttribute("propietario", propietario);
        model.addAttribute("redesSociales", redesSociales);
        model.addAttribute("servicios", servicios);
        model.addAttribute("proyectos", proyectos);
        return usuarioAutenticado ? "homeEdicion" : "redirect:/login";
    }
}
