package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Propietario;

public interface PropietarioService {

    Propietario guardar(Propietario propietario);

    boolean esUsuarioValido(String username, String password);

    Propietario ver();
}
