package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Habilidad;
import com.benimhoff.portfolioWeb.service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HabilidadRestController {

    @Autowired
    private HabilidadService habilidadService;

    @PostMapping("/api/habilidad")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody Habilidad habilidad){
        habilidadService.crear(habilidad);
    }

    @PutMapping("/api/habilidad")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody Habilidad habilidad){
        habilidadService.actualizar(habilidad);
    }

    @DeleteMapping("/api/habilidad/{idHabilidad}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idHabilidad){
        habilidadService.eliminar(idHabilidad);
    }
}
