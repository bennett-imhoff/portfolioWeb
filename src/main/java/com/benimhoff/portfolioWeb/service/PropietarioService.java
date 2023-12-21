package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Propietario;

public interface PropietarioService {

    Propietario guardar(Propietario propietario);

    Propietario validar(String username, String password);
}
