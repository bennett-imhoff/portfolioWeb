package com.benimhoff.portfolioWeb.domain;

import jakarta.persistence.*;

@Entity
@Table(name="habilidad")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Integer porcentaje;

    private String icono;

    private String imagen;

    private String nivel;

    private Long idHabilidadSubcategoria;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Long getIdHabilidadSubcategoria() {
        return idHabilidadSubcategoria;
    }

    public void setIdHabilidadSubcategoria(Long idHabilidadSubcategoria) {
        this.idHabilidadSubcategoria = idHabilidadSubcategoria;
    }
}
