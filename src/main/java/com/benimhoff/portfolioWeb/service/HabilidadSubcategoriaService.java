package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.HabilidadSubcategoria;

import java.util.List;

public interface HabilidadSubcategoriaService {

    HabilidadSubcategoria crear(HabilidadSubcategoria habilidadSubcategoria);

    HabilidadSubcategoria actualizar(HabilidadSubcategoria habilidadSubcategoria);

    void eliminar(Long idHabilidadSubcategoria);

    List<HabilidadSubcategoria> verTodas();
}
