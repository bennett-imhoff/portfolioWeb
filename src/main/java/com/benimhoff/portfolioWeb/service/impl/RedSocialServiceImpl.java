package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.domain.RedSocial;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.repository.RedSocialRepository;
import com.benimhoff.portfolioWeb.service.RedSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RedSocialServiceImpl implements RedSocialService {

    @Autowired
    private RedSocialRepository redSocialRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public RedSocial crear(RedSocial redSocial) {
        Assert.hasText(redSocial.getNombre(), "El nombre de la red social no puede quedar vacío.");
        Assert.hasText(redSocial.getEnlace(), "El enlace de la red social no puede quedar vacío.");
        Assert.hasText(redSocial.getIcono(), "Debe establecerse un ícono para la red social.");

        //Le asigno el propietario a la red social
        Optional<Propietario> propietarioRedSocial = propietarioRepository.findAll().stream().findFirst();
        propietarioRedSocial.ifPresent(propietario -> redSocial.setIdPropietario(propietario.getId()));
        Assert.notNull(redSocial.getIdPropietario(), "Debe existir un propietario para crear una red social.");

        return redSocialRepository.save(redSocial);
    }

    @Override
    public RedSocial actualizar(RedSocial redSocial){
        Assert.hasText(redSocial.getNombre(), "El nombre de la red social no puede quedar vacío.");
        Assert.hasText(redSocial.getEnlace(), "El enlace de la red social no puede quedar vacío.");
        Assert.hasText(redSocial.getIcono(), "Debe establecerse un ícono para la red social.");

        return redSocialRepository.save(redSocial);
    }

    @Override
    public void eliminar(Long idRedSocial){
        redSocialRepository.deleteById(idRedSocial);
    }

    @Override
    public List<RedSocial> verTodas() {
        return redSocialRepository.findAll();
    }
}
