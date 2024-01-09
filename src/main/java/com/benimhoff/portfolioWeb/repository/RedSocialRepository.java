package com.benimhoff.portfolioWeb.repository;

import com.benimhoff.portfolioWeb.domain.RedSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedSocialRepository extends JpaRepository<RedSocial, Long> {
}
