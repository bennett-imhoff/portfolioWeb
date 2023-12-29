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
    public Propietario guardar(Propietario propietario) {
        Assert.hasText(propietario.getNombre(), "El nombre es un campo obligatorio.");
        Assert.hasText(propietario.getApellido(), "El apellido es un campo obligatorio.");
        Assert.hasText(propietario.getDescripcion(), "La descripción es un campo obligatorio.");
        Assert.hasText(propietario.getUsername(), "El nombre de usuario es un campo obligatorio.");
        Assert.hasText(propietario.getPassword(), "La contraseña es un campo obligatorio.");

        List<Propietario> propietarios = propietarioRepository.findAll();
        Optional<Propietario> propietarioOpcional = propietarios.stream().findFirst();

        if (propietarioOpcional.isPresent()){
            Propietario propietarioExistente = propietarioOpcional.get();
            propietarioExistente.setNombre(propietario.getNombre());
            propietarioExistente.setApellido(propietario.getApellido());
            propietarioExistente.setDescripcion(propietario.getDescripcion());
            propietarioExistente.setUbicacion(propietario.getUbicacion());
            propietarioExistente.setCorreo(propietario.getCorreo());
            propietarioExistente.setTelefono(propietario.getTelefono());
            propietarioExistente.setUsername(propietario.getUsername());
            propietarioExistente.setPassword(propietario.getPassword());
            return propietarioRepository.save(propietarioExistente);
        }
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
