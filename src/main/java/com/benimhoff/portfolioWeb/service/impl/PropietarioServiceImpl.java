package com.benimhoff.portfolioWeb.service.impl;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PropietarioServiceImpl implements PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public Propietario actualizar(Propietario propietario) {
        Assert.hasText(propietario.getNombre(), "El nombre es un campo obligatorio.");
        Assert.hasText(propietario.getApellido(), "El apellido es un campo obligatorio.");
        Assert.hasText(propietario.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.hasText(propietario.getUsername(), "El nombre de usuario es un campo obligatorio.");
        Assert.hasText(propietario.getPassword(), "La contraseña es un campo obligatorio.");
        Assert.hasText(propietario.getTextoSoy(), "El texto de quien sos es un campo obligatorio.");
        Assert.hasText(propietario.getTextoQuiero(), "El texto de qué querés es un campo obligatorio.");
        Assert.hasText(propietario.getTextoHago(), "El texto de qué hacés es un campo obligatorio.");

        return propietarioRepository.save(propietario);
    }

    @Override
    public boolean esUsuarioValido(String username, String password) {
        List<Propietario> propietarios = propietarioRepository.findAll();
        Propietario propietarioGuardado = propietarios.stream().findFirst().get();
        Assert.isTrue(propietarioGuardado.getUsername().equals(username), "El usuario es incorrecto.");
        Assert.isTrue(propietarioGuardado.getPassword().equals(password), "La contraseña es incorrecta.");

        return propietarioGuardado.getUsername().equals(username) && propietarioGuardado.getPassword().equals(password);
    }

    @Override
    public Propietario ver(){
        List<Propietario> propietarios = propietarioRepository.findAll();
        return propietarios.stream().findFirst().get();
    }
}
