package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.RedSocial;

import java.util.List;

public interface RedSocialService {

    RedSocial crear(RedSocial redSocial);

    List<RedSocial> verTodas();
}
