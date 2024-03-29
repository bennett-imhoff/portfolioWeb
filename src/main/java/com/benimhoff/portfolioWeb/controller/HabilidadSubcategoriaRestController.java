package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.HabilidadSubcategoria;
import com.benimhoff.portfolioWeb.service.HabilidadSubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HabilidadSubcategoriaRestController {

    @Autowired
    private HabilidadSubcategoriaService habilidadSubcategoriaService;

    @PostMapping("/api/habilidadSubcategoria")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody HabilidadSubcategoria habilidadSubcategoria) {
        habilidadSubcategoriaService.crear(habilidadSubcategoria);
    }

    @PutMapping("/api/habilidadSubcategoria")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizar(@RequestBody HabilidadSubcategoria habilidadSubcategoria) {
        habilidadSubcategoriaService.actualizar(habilidadSubcategoria);
    }

    @DeleteMapping("/api/habilidadSubcategoria/{idHabilidadSubcategoria}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long idHabilidadSubcategoria) {
        habilidadSubcategoriaService.eliminar(idHabilidadSubcategoria);
    }

    @GetMapping("/api/habilidadSubcategoria/{idHabilidadSubcategoria}")
    public ResponseEntity<HabilidadSubcategoria> obtenerSubcategoria(@PathVariable Long idHabilidadSubcategoria) {
        HabilidadSubcategoria subcategoria = habilidadSubcategoriaService.obtenerPorId(idHabilidadSubcategoria);

        if (subcategoria != null) {
            return new ResponseEntity<>(subcategoria, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
