package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.ExperienciaLaboral;

import java.util.List;

public interface ExperienciaLaboralService {

    ExperienciaLaboral crear (ExperienciaLaboral experienciaLaboral);

    ExperienciaLaboral actualizar (ExperienciaLaboral experienciaLaboral);

    void eliminar (Long idExperienciaLaboral);

    List<ExperienciaLaboral> verTodas ();
}
