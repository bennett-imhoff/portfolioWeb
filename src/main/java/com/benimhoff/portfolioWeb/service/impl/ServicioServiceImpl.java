package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.domain.Servicio;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.repository.ServicioRepository;
import com.benimhoff.portfolioWeb.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio crear(Servicio servicio) {
        Assert.hasText(servicio.getIcono(), "El servicio debe tener un ícono asociado.");
        Assert.hasText(servicio.getTitulo(), "El servicio debe tener un título asociado.");
        Assert.hasText(servicio.getDescripcion(), "El servicio debe tener una descripción asociada.");

        //Le asigno el propietario al servicio
        Optional<Propietario> propietarioServicio = propietarioRepository.findAll().stream().findFirst();
        propietarioServicio.ifPresent(propietario -> servicio.setIdPropietario(propietario.getId()));
        Assert.notNull(servicio.getIdPropietario(), "Debe existir un propietario para crear un servicio.");

        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio actualizar(Servicio servicio){
        Assert.hasText(servicio.getIcono(), "El servicio debe tener un ícono asociado.");
        Assert.hasText(servicio.getTitulo(), "El servicio debe tener un título asociado.");
        Assert.hasText(servicio.getDescripcion(), "El servicio debe tener una descripción asociada.");

        return servicioRepository.save(servicio);
    }

    @Override
    public void eliminar(Long idServicio){
        servicioRepository.deleteById(idServicio);
    }

    @Override
    public List<Servicio> verTodos(){
        return servicioRepository.findAll();
    }
}
