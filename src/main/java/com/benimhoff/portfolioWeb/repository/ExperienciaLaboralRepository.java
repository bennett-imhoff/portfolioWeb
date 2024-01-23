package com.benimhoff.portfolioWeb.repository;

import com.benimhoff.portfolioWeb.domain.ExperienciaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, Long> {
}
