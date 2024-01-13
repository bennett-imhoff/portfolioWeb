package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.domain.Proyecto;
import com.benimhoff.portfolioWeb.domain.RedSocial;
import com.benimhoff.portfolioWeb.domain.Servicio;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import com.benimhoff.portfolioWeb.service.ProyectoService;
import com.benimhoff.portfolioWeb.service.RedSocialService;
import com.benimhoff.portfolioWeb.service.ServicioService;
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

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/")
    public String home (Model model){
        Propietario propietario = propietarioService.ver();
        List<RedSocial> redesSociales = redSocialService.verTodas();
        List<Servicio> servicios = servicioService.verTodos();
        List<Proyecto> proyectos = proyectoService.verTodos();
        model.addAttribute("propietario", propietario);
        model.addAttribute("redesSociales", redesSociales);
        model.addAttribute("servicios", servicios);
        model.addAttribute("proyectos", proyectos);
        return "home";
    }
}
