package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.*;
import com.benimhoff.portfolioWeb.exception.LoginException;
import com.benimhoff.portfolioWeb.service.*;
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

    @Autowired
    private HabilidadCategoriaService habilidadCategoriaService;

    @Autowired
    private HabilidadSubcategoriaService habilidadSubcategoriaService;

    @Autowired
    private HabilidadService habilidadService;

    @Autowired
    private ExperienciaLaboralService experienciaLaboralService;

    @Autowired
    private EducacionService educacionService;

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
        List<HabilidadCategoria> categorias = habilidadCategoriaService.verTodas();
        List<HabilidadSubcategoria> subcategorias = habilidadSubcategoriaService.verTodas();
        List<Habilidad> habilidades = habilidadService.verTodas();
        List<ExperienciaLaboral> experienciasLaborales = experienciaLaboralService.verTodas();
        List<Educacion> educaciones = educacionService.verTodas();

        model.addAttribute("propietario", propietario);
        model.addAttribute("redesSociales", redesSociales);
        model.addAttribute("servicios", servicios);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("subcategorias", subcategorias);
        model.addAttribute("habilidades", habilidades);
        model.addAttribute("experienciasLaborales", experienciasLaborales);
        model.addAttribute("educaciones", educaciones);

        return usuarioAutenticado ? "homeEdicion" : "redirect:/login";
    }
}
