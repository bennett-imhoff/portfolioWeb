package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.ExperienciaLaboral;
import com.benimhoff.portfolioWeb.service.ExperienciaLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExperienciaLaboralRestController {

    @Autowired
    private ExperienciaLaboralService experienciaLaboralService;

    @PostMapping("/api/experienciaLaboral")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody ExperienciaLaboral experienciaLaboral){
        experienciaLaboralService.crear(experienciaLaboral);
    }

    @PutMapping("/api/experienciaLaboral")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody ExperienciaLaboral experienciaLaboral){
        experienciaLaboralService.actualizar(experienciaLaboral);
    }

    @DeleteMapping("/api/experienciaLaboral/{idExperienciaLaboral}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idExperienciaLaboral){
        experienciaLaboralService.eliminar(idExperienciaLaboral);
    }
}
