package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.RedSocial;

import java.util.List;

public interface RedSocialService {

    RedSocial crear(RedSocial redSocial);

    RedSocial actualizar(RedSocial redSocial);

    List<RedSocial> verTodas();
}
