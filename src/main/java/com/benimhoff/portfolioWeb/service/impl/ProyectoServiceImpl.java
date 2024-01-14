package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Proyecto;
import com.benimhoff.portfolioWeb.service.ProyectoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {
    @Override
    public Proyecto crear(Proyecto proyecto) {
        return null;
    }

    @Override
    public Proyecto actualizar(Proyecto proyecto) {
        return null;
    }

    @Override
    public void eliminar(Long idProyecto) {

    }

    @Override
    public List<Proyecto> verTodos() {
        return null;
    }
}
