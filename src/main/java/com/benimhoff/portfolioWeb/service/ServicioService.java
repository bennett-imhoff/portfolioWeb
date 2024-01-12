package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Servicio;

import java.util.List;

public interface ServicioService {

    Servicio crear(Servicio servicio);

    Servicio actualizar(Servicio servicio);

    void eliminar(Long idServicio);

    List<Servicio> verTodos();
}
