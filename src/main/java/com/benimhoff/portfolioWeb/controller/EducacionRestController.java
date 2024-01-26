package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Educacion;
import com.benimhoff.portfolioWeb.service.EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class EducacionRestController {

    @Autowired
    private EducacionService educacionService;

    @PostMapping("/api/educacion")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody Educacion educacion) {
        educacionService.crear(educacion);
    }

    @PutMapping("/api/educacion")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody Educacion educacion) {
        educacionService.actualizar(educacion);
    }

    @DeleteMapping("/api/educacion/{idEducacion}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idEducacion) {
        educacionService.eliminar(idEducacion);
    }
}
