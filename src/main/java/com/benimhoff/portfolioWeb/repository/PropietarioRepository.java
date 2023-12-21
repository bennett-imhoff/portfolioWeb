package com.benimhoff.portfolioWeb.repository;

import com.benimhoff.portfolioWeb.domain.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Long> {
}
