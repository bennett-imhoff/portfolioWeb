package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Habilidad;

import java.util.List;

public interface HabilidadService {

    Habilidad crear(Habilidad habilidad);

    Habilidad actualizar(Habilidad habilidaD);

    void eliminar(Long idHabilidad);

    List<Habilidad> verTodas();
}
