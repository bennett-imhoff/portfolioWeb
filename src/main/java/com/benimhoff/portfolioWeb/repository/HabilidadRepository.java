package com.benimhoff.portfolioWeb.repository;

import com.benimhoff.portfolioWeb.domain.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {
}
