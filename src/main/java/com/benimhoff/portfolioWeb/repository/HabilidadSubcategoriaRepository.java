package com.benimhoff.portfolioWeb.repository;

import com.benimhoff.portfolioWeb.domain.HabilidadSubcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadSubcategoriaRepository extends JpaRepository<HabilidadSubcategoria, Long> {
}
