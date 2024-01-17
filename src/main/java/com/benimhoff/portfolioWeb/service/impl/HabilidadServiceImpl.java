package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Habilidad;
import com.benimhoff.portfolioWeb.domain.HabilidadSubcategoria;
import com.benimhoff.portfolioWeb.repository.HabilidadRepository;
import com.benimhoff.portfolioWeb.service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class HabilidadServiceImpl implements HabilidadService {

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Override
    public Habilidad crear(Habilidad habilidad) {
        Assert.hasText(habilidad.getNombre(), "La habilidad debe tener un nombre asignado.");
        Assert.notNull(habilidad.getIdHabilidadSubcategoria(), "La habilidad debe ser de alguna subcategoría.");

        return habilidadRepository.save(habilidad);
    }

    @Override
    public Habilidad actualizar(Habilidad habilidad) {
        Assert.hasText(habilidad.getNombre(), "La habilidad debe tener un nombre asignado.");
        Assert.notNull(habilidad.getIdHabilidadSubcategoria(), "La habilidad debe ser de alguna categoría.");

        return habilidadRepository.save(habilidad);
    }

    @Override
    public void eliminar(Long idHabilidad) {
        habilidadRepository.deleteById(idHabilidad);
    }

    @Override
    public List<Habilidad> verTodas() {
        return habilidadRepository.findAll();
    }
}
