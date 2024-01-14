package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Proyecto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class ProyectoServiceTest {

    @Autowired
    private ProyectoService proyectoService;

    @Test
    public void crear_conDatosValidos_guardaProyecto(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo("Titulo del proyecto");
        proyecto.setResumen("Resumen del proyeto");
        proyecto.setDescripcion("Descripcion del proyecto");
        proyecto.setImagen("urldeimagen.com");
        proyecto.setIdPropietario(1L);
        proyecto = proyectoService.crear(proyecto);

        assertNotNull(proyecto.getId());
    }
}
