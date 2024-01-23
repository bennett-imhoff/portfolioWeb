package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.*;
import com.benimhoff.portfolioWeb.service.*;
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

    @Autowired
    private HabilidadCategoriaService habilidadCategoriaService;

    @Autowired
    private ExperienciaLaboralService experienciaLaboralService;

    @GetMapping("/")
    public String home (Model model){
        Propietario propietario = propietarioService.ver();
        List<RedSocial> redesSociales = redSocialService.verTodas();
        List<Servicio> servicios = servicioService.verTodos();
        List<Proyecto> proyectos = proyectoService.verTodos();
        List<HabilidadCategoria> categorias = habilidadCategoriaService.verTodas();
        List<ExperienciaLaboral> experienciasLaborales = experienciaLaboralService.verTodas();

        model.addAttribute("propietario", propietario);
        model.addAttribute("redesSociales", redesSociales);
        model.addAttribute("servicios", servicios);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("experienciasLaborales", experienciasLaborales);

        return "home";
    }
}
