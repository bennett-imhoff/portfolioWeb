package com.benimhoff.portfolioWeb.repository;

import com.benimhoff.portfolioWeb.domain.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
