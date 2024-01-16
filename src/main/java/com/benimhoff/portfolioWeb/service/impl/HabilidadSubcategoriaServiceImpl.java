package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.HabilidadSubcategoria;
import com.benimhoff.portfolioWeb.repository.HabilidadSubcategoriaRepository;
import com.benimhoff.portfolioWeb.service.HabilidadSubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class HabilidadSubcategoriaServiceImpl implements HabilidadSubcategoriaService {

    @Autowired
    private HabilidadSubcategoriaRepository habilidadSubcategoriaRepository;
    @Override
    public HabilidadSubcategoria crear(HabilidadSubcategoria habilidadSubcategoria) {
        Assert.hasText(habilidadSubcategoria.getNombre(), "La subcategoría debe tener un nombre.");
        Assert.notNull(habilidadSubcategoria.getIdHabilidadCategoria(), "La subcategoría debe estar asociada a una categoría");

        return habilidadSubcategoriaRepository.save(habilidadSubcategoria);
    }

    @Override
    public HabilidadSubcategoria actualizar(HabilidadSubcategoria habilidadSubcategoria) {
        Assert.hasText(habilidadSubcategoria.getNombre(), "La subcategoría debe tener un nombre.");
        Assert.notNull(habilidadSubcategoria.getIdHabilidadCategoria(), "La subcategoría debe estar asociada a una categoría");

        return habilidadSubcategoriaRepository.save(habilidadSubcategoria);
    }

    @Override
    public void eliminar(Long idHabilidadSubcategoria) {
        habilidadSubcategoriaRepository.deleteById(idHabilidadSubcategoria);
    }

    @Override
    public List<HabilidadSubcategoria> verTodas() {
        return habilidadSubcategoriaRepository.findAll();
    }
}
