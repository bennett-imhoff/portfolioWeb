package com.benimhoff.portfolioWeb.domain;

import jakarta.persistence.*;

@Entity
@Table(name="habilidad_subcategoria")
public class HabilidadSubcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Boolean necesitaDescripcion;

    private Boolean necesitaPorcentaje;

    private Boolean necesitaIcono;

    private Boolean necesitaImagen;

    private Boolean necesitaNivel;

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

    public Boolean isNecesitaDescripcion() {
        return necesitaDescripcion;
    }

    public void setNecesitaDescripcion(Boolean necesitaDescripcion) {
        this.necesitaDescripcion = necesitaDescripcion;
    }

    public Boolean isNecesitaPorcentaje() {
        return necesitaPorcentaje;
    }

    public void setNecesitaPorcentaje(Boolean necesitaPorcentaje) {
        this.necesitaPorcentaje = necesitaPorcentaje;
    }

    public Boolean isNecesitaIcono() {
        return necesitaIcono;
    }

    public void setNecesitaIcono(Boolean necesitaIcono) {
        this.necesitaIcono = necesitaIcono;
    }

    public Boolean isNecesitaImagen() {
        return necesitaImagen;
    }

    public void setNecesitaImagen(Boolean necesitaImagen) {
        this.necesitaImagen = necesitaImagen;
    }

    public Boolean isNecesitaNivel() {
        return necesitaNivel;
    }

    public void setNecesitaNivel(Boolean necesitaNivel) {
        this.necesitaNivel = necesitaNivel;
    }

    public Long getIdHabilidadCategoria() {
        return idHabilidadCategoria;
    }

    public void setIdHabilidadCategoria(Long idHabilidadCategoria) {
        this.idHabilidadCategoria = idHabilidadCategoria;
    }
}
