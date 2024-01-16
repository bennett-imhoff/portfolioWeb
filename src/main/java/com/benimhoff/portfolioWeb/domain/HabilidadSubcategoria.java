package com.benimhoff.portfolioWeb.domain;

import jakarta.persistence.*;

@Entity
@Table(name="habilidad_subcategoria")
public class HabilidadSubcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Long idHabilidadCategoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdHabilidadCategoria() {
        return idHabilidadCategoria;
    }

    public void setIdHabilidadCategoria(Long idHabilidadCategoria) {
        this.idHabilidadCategoria = idHabilidadCategoria;
    }
}
