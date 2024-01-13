package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.domain.Proyecto;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.repository.ProyectoRepository;
import com.benimhoff.portfolioWeb.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public Proyecto crear(Proyecto proyecto) {
        Assert.hasText(proyecto.getTitulo(), "El titulo es un campo obligatorio.");
        Assert.hasText(proyecto.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.hasText(proyecto.getImagen(), "La imagen es un campo obligatorio.");

        //Le asigno el propietario a la red social
        Optional<Propietario> propietarioProyecto = propietarioRepository.findAll().stream().findFirst();
        propietarioProyecto.ifPresent(propietario -> proyecto.setIdPropietario(propietario.getId()));
        Assert.notNull(proyecto.getIdPropietario(), "Debe existir un propietario para crear una red social.");

        return proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto actualizar(Proyecto proyecto) {
        Assert.hasText(proyecto.getTitulo(), "El titulo es un campo obligatorio.");
        Assert.hasText(proyecto.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.hasText(proyecto.getImagen(), "La imagen es un campo obligatorio.");

        return proyectoRepository.save(proyecto);
    }

    @Override
    public void eliminar(Long idProyecto) {
        proyectoRepository.deleteById(idProyecto);
    }

    @Override
    public List<Proyecto> verTodos() {
        return proyectoRepository.findAll();
    }
}
