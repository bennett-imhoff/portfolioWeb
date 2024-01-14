package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Proyecto;

import java.util.List;

public interface ProyectoService {

    Proyecto crear(Proyecto proyecto);

    Proyecto actualizar(Proyecto proyecto);

    void eliminar(Long idProyecto);

    List<Proyecto> verTodos();
}
