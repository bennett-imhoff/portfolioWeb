package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.HabilidadCategoria;
import com.benimhoff.portfolioWeb.domain.HabilidadSubcategoria;
import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.service.HabilidadCategoriaService;
import com.benimhoff.portfolioWeb.service.HabilidadSubcategoriaService;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HabilidadController {

    @Autowired
    private HabilidadCategoriaService habilidadCategoriaService;

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    private HabilidadSubcategoriaService habilidadSubcategoriaService;

    @GetMapping("/habilidad/{idCategoria}")
    public String habilidad(@PathVariable Long idCategoria, Model model){
        HabilidadCategoria categoria = habilidadCategoriaService.obtenerPorId(idCategoria);
        List<HabilidadSubcategoria> subcategorias = habilidadSubcategoriaService.verTodas();
        Propietario propietario = propietarioService.ver();
        model.addAttribute("categoria", categoria);
        model.addAttribute("subcategorias", subcategorias);
        model.addAttribute("propietario", propietario);

        return "habilidad";
    }
}
