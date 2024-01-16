package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Servicio;
import com.benimhoff.portfolioWeb.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServicioRestController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping("/api/servicio")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody Servicio servicio){
        servicioService.crear(servicio);
    }

    @PutMapping("/api/servicio")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody Servicio servicio){
        servicioService.actualizar(servicio);
    }

    @DeleteMapping("/api/servicio/{idServicio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idServicio){
        servicioService.eliminar(idServicio);
    }
}
