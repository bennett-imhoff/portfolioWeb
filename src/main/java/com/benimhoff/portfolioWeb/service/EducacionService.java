package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Educacion;

import java.util.List;

public interface EducacionService {

    Educacion crear(Educacion educacion);

    Educacion actualizar(Educacion educacion);

    void eliminar(Long idEducacion);

    List<Educacion> verTodas();
}
