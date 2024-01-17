package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.HabilidadCategoria;

import java.util.List;

public interface HabilidadCategoriaService {

    HabilidadCategoria crear(HabilidadCategoria habilidadCategoria);

    HabilidadCategoria actualizar(HabilidadCategoria habilidadCategoria);

    void eliminar(Long idHabilidadCategoria);

    List<HabilidadCategoria> verTodas();

    HabilidadCategoria obtenerPorId(Long idHabilidadCategoria);
}
