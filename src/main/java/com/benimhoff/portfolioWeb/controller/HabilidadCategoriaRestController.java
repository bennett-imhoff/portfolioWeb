package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.HabilidadCategoria;
import com.benimhoff.portfolioWeb.service.HabilidadCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HabilidadCategoriaRestController {

    @Autowired
    private HabilidadCategoriaService habilidadCategoriaService;

    @PostMapping("/api/habilidadCategoria")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody HabilidadCategoria habilidadCategoria){
        habilidadCategoriaService.crear(habilidadCategoria);
    }

    @PutMapping("/api/habilidadCategoria")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody HabilidadCategoria habilidadCategoria){
        habilidadCategoriaService.actualizar(habilidadCategoria);
    }

    @DeleteMapping("/api/habilidadCategoria/{idHabilidadCategoria}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idHabilidadCategoria){
        habilidadCategoriaService.eliminar(idHabilidadCategoria);
    }
}
