package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropietarioRestController {

    @Autowired
    private PropietarioService propietarioService;

    @PutMapping("/api/propietario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody Propietario propietario) {
        propietarioService.actualizar(propietario);
    }
}
