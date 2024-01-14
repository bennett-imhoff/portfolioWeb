package com.benimhoff.portfolioWeb.domain;

import jakarta.persistence.*;

@Entity
@Table(name="proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String resumen;

    private String descripcion;

    private String imagen;

    private String enlaceGithub;

    private String enlaceDemo;

    private Long idPropietario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEnlaceGithub() {
        return enlaceGithub;
    }

    public void setEnlaceGithub(String enlaceGithub) {
        this.enlaceGithub = enlaceGithub;
    }

    public String getEnlaceDemo() {
        return enlaceDemo;
    }

    public void setEnlaceDemo(String enlaceDemo) {
        this.enlaceDemo = enlaceDemo;
    }

    public Long getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Long idPropietario) {
        this.idPropietario = idPropietario;
    }
}
