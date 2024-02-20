package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Educacion;
import com.benimhoff.portfolioWeb.repository.EducacionRepository;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.service.EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class EducacionServiceImpl implements EducacionService {

    @Autowired
    private EducacionRepository educacionRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public Educacion crear(Educacion educacion) {
        validarEducacion(educacion);
        asignarPropietario(educacion);

        return educacionRepository.save(educacion);
    }

    private void asignarPropietario(Educacion educacion) {
        propietarioRepository.findAll().stream()
                .findFirst()
                .ifPresent(propietario -> educacion.setIdPropietario(propietario.getId()));

        Assert.notNull(educacion.getIdPropietario(), "Debe existir un propietario para crear una educación.");
    }

    @Override
    public Educacion actualizar(Educacion educacion) {
        validarEducacion(educacion);

        return educacionRepository.save(educacion);
    }

    private void validarEducacion(Educacion educacion) {
        Assert.hasText(educacion.getTitulo(), "El título es un campo obligatorio.");
        Assert.hasText(educacion.getLugar(), "El lugar es un campo obligatorio.");
        Assert.hasText(educacion.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.notNull(educacion.getFechaInicio(), "La fecha de inicio es un campo obligatorio.");
        Assert.hasText(educacion.getIcono(), "El icono es un campo obligatorio.");
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
