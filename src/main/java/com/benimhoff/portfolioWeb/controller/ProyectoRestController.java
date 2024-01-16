package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Proyecto;
import com.benimhoff.portfolioWeb.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProyectoRestController {

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping("/api/proyecto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody Proyecto proyecto){
        proyectoService.crear(proyecto);
    }

    @PutMapping("/api/proyecto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody Proyecto proyecto){
        proyectoService.actualizar(proyecto);
    }

    @DeleteMapping("/api/proyecto/{idProyecto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idProyecto){
        proyectoService.eliminar(idProyecto);
    }
}
