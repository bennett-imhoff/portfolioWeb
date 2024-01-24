package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Educacion;
import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.repository.EducacionRepository;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.service.EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducacionServiceImpl implements EducacionService {

    @Autowired
    private EducacionRepository educacionRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public Educacion crear(Educacion educacion) {
        Assert.hasText(educacion.getTitulo(), "El título es un campo obligatorio.");
        Assert.hasText(educacion.getLugar(), "El lugar es un campo obligatorio.");
        Assert.hasText(educacion.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.notNull(educacion.getFechaInicio(), "La fecha de inicio es un campo obligatorio.");
        Assert.hasText(educacion.getIcono(), "El icono es un campo obligatorio.");

        //Le asigno el propietario a la educacion
        Optional<Propietario> propietarioExperiencia = propietarioRepository.findAll().stream().findFirst();
        propietarioExperiencia.ifPresent(propietario -> educacion.setIdPropietario(propietario.getId()));
        Assert.notNull(educacion.getIdPropietario(), "Debe existir un propietario para crear una educación.");

        return educacionRepository.save(educacion);
    }

    @Override
    public Educacion actualizar(Educacion educacion) {
        Assert.hasText(educacion.getTitulo(), "El título es un campo obligatorio.");
        Assert.hasText(educacion.getLugar(), "El lugar es un campo obligatorio.");
        Assert.hasText(educacion.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.notNull(educacion.getFechaInicio(), "La fecha de inicio es un campo obligatorio.");
        Assert.hasText(educacion.getIcono(), "El icono es un campo obligatorio.");

        return educacionRepository.save(educacion);
    }

    @Override
    public void eliminar(Long idEducacion) {
        educacionRepository.deleteById(idEducacion);
    }

    @Override
    public List<Educacion> verTodas() {
        return educacionRepository.findAll();
    }
}
