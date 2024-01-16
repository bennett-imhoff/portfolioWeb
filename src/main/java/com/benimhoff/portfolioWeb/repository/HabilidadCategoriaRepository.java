package com.benimhoff.portfolioWeb.repository;

import com.benimhoff.portfolioWeb.domain.HabilidadCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadCategoriaRepository extends JpaRepository<HabilidadCategoria, Long> {
}
