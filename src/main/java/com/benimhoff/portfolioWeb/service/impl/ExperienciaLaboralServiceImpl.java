package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.ExperienciaLaboral;
import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.repository.ExperienciaLaboralRepository;
import com.benimhoff.portfolioWeb.service.ExperienciaLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienciaLaboralServiceImpl implements ExperienciaLaboralService {

    @Autowired
    private ExperienciaLaboralRepository experienciaLaboralRepository;

    @Override
    public ExperienciaLaboral crear(ExperienciaLaboral experienciaLaboral) {
        Assert.hasText(experienciaLaboral.getPuesto(), "El puesto es un campo obligatorio.");
        Assert.hasText(experienciaLaboral.getEmpresa(), "La empresa es un campo obligatorio.");
        Assert.hasText(experienciaLaboral.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.notNull(experienciaLaboral.getFechaInicio(), "La fecha de inicio debe estar definida.");
        Assert.hasText(experienciaLaboral.getIcono(), "El icono es un campo obligatorio.");

        //Le asigno el propietario a la experiencia laboral
        Optional<ExperienciaLaboral> propietarioExperiencia = experienciaLaboralRepository.findAll().stream().findFirst();
        propietarioExperiencia.ifPresent(propietario -> experienciaLaboral.setIdPropietario(propietario.getId()));
        Assert.notNull(experienciaLaboral.getIdPropietario(), "Debe existir un propietario para crear una experiencia laboral.");

        return experienciaLaboralRepository.save(experienciaLaboral);
    }

    @Override
    public ExperienciaLaboral actualizar(ExperienciaLaboral experienciaLaboral) {
        Assert.hasText(experienciaLaboral.getPuesto(), "El puesto es un campo obligatorio.");
        Assert.hasText(experienciaLaboral.getEmpresa(), "La empresa es un campo obligatorio.");
        Assert.hasText(experienciaLaboral.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.notNull(experienciaLaboral.getFechaInicio(), "La fecha de inicio debe estar definida.");
        Assert.hasText(experienciaLaboral.getIcono(), "El icono es un campo obligatorio.");

        return experienciaLaboralRepository.save(experienciaLaboral);
    }

    @Override
    public void eliminar(Long idExperienciaLaboral) {
        experienciaLaboralRepository.deleteById(idExperienciaLaboral);
    }

    @Override
    public List<ExperienciaLaboral> verTodas() {
        return experienciaLaboralRepository.findAll();
    }
}
