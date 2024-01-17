package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.HabilidadCategoria;
import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.repository.HabilidadCategoriaRepository;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.service.HabilidadCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabilidadCategoriaServiceImpl implements HabilidadCategoriaService {

    @Autowired
    private HabilidadCategoriaRepository habilidadCategoriaRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public HabilidadCategoria crear(HabilidadCategoria habilidadCategoria) {
        Assert.hasText(habilidadCategoria.getNombre(), "La categoría debe tener un nombre asignado.");
        Assert.hasText(habilidadCategoria.getDescripcion(), "La categoría debe tener una descripción asignada.");
        Assert.hasText(habilidadCategoria.getIcono(), "La categoría debe tener un ícono asociado.");
        Assert.hasText(habilidadCategoria.getColorIcono(), "Debes asignarle un color al ícono de la categoría.");

        //Le asigno el propietario a la categoría
        Optional<Propietario> propietarioProyecto = propietarioRepository.findAll().stream().findFirst();
        propietarioProyecto.ifPresent(propietario -> habilidadCategoria.setIdPropietario(propietario.getId()));
        Assert.notNull(habilidadCategoria.getIdPropietario(), "Debe existir un propietario para crear una categoría.");

        return habilidadCategoriaRepository.save(habilidadCategoria);
    }

    @Override
    public HabilidadCategoria actualizar(HabilidadCategoria habilidadCategoria) {
        Assert.hasText(habilidadCategoria.getNombre(), "La categoría debe tener un nombre asignado.");
        Assert.hasText(habilidadCategoria.getDescripcion(), "La categoría debe tener una descripción asignada.");
        Assert.hasText(habilidadCategoria.getIcono(), "La categoría debe tener un ícono asociado.");
        Assert.hasText(habilidadCategoria.getColorIcono(), "Debes asignarle un color al ícono de la categoría.");

        return habilidadCategoriaRepository.save(habilidadCategoria);
    }

    @Override
    public void eliminar(Long idHabilidadCategoria) {
        habilidadCategoriaRepository.deleteById(idHabilidadCategoria);
    }

    @Override
    public List<HabilidadCategoria> verTodas() {
        return habilidadCategoriaRepository.findAll();
    }

    @Override
    public HabilidadCategoria obtenerPorId(Long idHabilidadCategoria) {
        Assert.isTrue(habilidadCategoriaRepository.existsById(idHabilidadCategoria), "La categoría debe existir.");

        return habilidadCategoriaRepository.findById(idHabilidadCategoria).get();
    }
}
