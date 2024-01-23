package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Proyecto;
import com.benimhoff.portfolioWeb.repository.ProyectoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class ProyectoServiceTest {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Test
    public void crear_conDatosValidos_guardaProyecto(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo("Titulo del proyecto");
        proyecto.setResumen("Resumen del proyecto");
        proyecto.setDescripcion("Descripcion del proyecto");
        proyecto.setImagen("urldeimagen.com");
        proyecto.setIdPropietario(1L);
        proyecto = proyectoService.crear(proyecto);

        assertNotNull(proyecto.getId());
    }

    @Test
    public void crear_conCamposEnBlanco_lanzaExcepcion(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo(" ");
        proyecto.setResumen(" ");
        proyecto.setDescripcion(" ");
        proyecto.setImagen(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            proyectoService.crear(proyecto);
        });
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo(null);
        proyecto.setResumen(null);
        proyecto.setDescripcion(null);
        proyecto.setImagen(null);

        assertThrows(IllegalArgumentException.class, ()->{
            proyectoService.crear(proyecto);
        });
    }

    @Test
    public void crear_conIdPropietarioNulo_lanzaExcepcion(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo("Titulo del proyecto");
        proyecto.setResumen("Resumen del proyecto");
        proyecto.setDescripcion("Descripcion del proyecto");
        proyecto.setImagen("urldeimagen.com");

        assertThrows(IllegalArgumentException.class, ()->{
            proyectoService.crear(proyecto);
        });
    }

    @Test
    public void actualizar_conDatosValidos_actualizaProyecto(){
        Proyecto proyectoAntiguo = new Proyecto();
        proyectoAntiguo.setTitulo("Titulo del proyecto antiguo");
        proyectoAntiguo.setResumen("Resumen del proyecto antiguo");
        proyectoAntiguo.setDescripcion("Descripcion del proyecto antiguo");
        proyectoAntiguo.setImagen("urldeimagenproyectoantiguo.com");
        proyectoAntiguo.setIdPropietario(1L);
        proyectoAntiguo = proyectoRepository.save(proyectoAntiguo);

        Proyecto proyectoNuevo = new Proyecto();
        proyectoNuevo.setId(proyectoAntiguo.getId());
        proyectoNuevo.setTitulo("Titulo del proyecto nuevo");
        proyectoNuevo.setResumen("Resumen del proyecto nuevo");
        proyectoNuevo.setDescripcion("Descripcion del proyecto nuevo");
        proyectoNuevo.setImagen("urldeimagenproyectonuevo.com");
        proyectoService.actualizar(proyectoNuevo);

        Proyecto proyectoGuardado = proyectoRepository.findById(proyectoAntiguo.getId()).get();

        assertEquals(proyectoAntiguo.getId(), proyectoNuevo.getId());
        assertAll("Verifico que todos los datos se hayan actualizado.",
            ()-> assertEquals("Titulo del proyecto nuevo", proyectoGuardado.getTitulo()),
            ()-> assertEquals("Resumen del proyecto nuevo", proyectoGuardado.getResumen()),
            ()-> assertEquals("Descripcion del proyecto nuevo", proyectoGuardado.getDescripcion()),
            ()-> assertEquals("urldeimagenproyectonuevo.com", proyectoGuardado.getImagen())
        );
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        Proyecto proyectoAntiguo = new Proyecto();
        proyectoAntiguo.setTitulo("Titulo del proyecto antiguo");
        proyectoAntiguo.setResumen("Resumen del proyecto antiguo");
        proyectoAntiguo.setDescripcion("Descripcion del proyecto antiguo");
        proyectoAntiguo.setImagen("urldeimagenproyectoantiguo.com");
        proyectoAntiguo.setIdPropietario(1L);
        proyectoAntiguo = proyectoRepository.save(proyectoAntiguo);

        Proyecto proyectoNuevo = proyectoRepository.findById(proyectoAntiguo.getId()).get();
        proyectoNuevo.setTitulo(" ");
        proyectoNuevo.setResumen(" ");
        proyectoNuevo.setDescripcion(" ");
        proyectoNuevo.setImagen(null);

        assertThrows(IllegalArgumentException.class, ()->{
           proyectoService.actualizar(proyectoNuevo);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaProyecto(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo("Titulo del proyecto");
        proyecto.setResumen("Resumen del proyecto");
        proyecto.setDescripcion("Descripcion del proyecto");
        proyecto.setImagen("urldeimagenproyecto.com");
        proyecto.setIdPropietario(1L);
        proyecto = proyectoRepository.save(proyecto);

        proyectoService.eliminar(proyecto.getId());

        assertFalse(proyectoRepository.existsById(proyecto.getId()));
    }

    @Test
    public void verTodos_conCualquierUsuario_retornaListaProyectos(){
        Proyecto proyectoUno = new Proyecto();
        proyectoUno.setTitulo("Titulo del proyecto uno");
        proyectoUno.setResumen("Resumen del proyecto uno");
        proyectoUno.setDescripcion("Descripcion del proyecto uno");
        proyectoUno.setImagen("urldeimagenproyectouno.com");
        proyectoUno.setIdPropietario(1L);
        proyectoUno = proyectoRepository.save(proyectoUno);

        Proyecto proyectoDos = new Proyecto();
        proyectoDos.setTitulo("Titulo del proyecto dos");
        proyectoDos.setResumen("Resumen del proyecto dos");
        proyectoDos.setDescripcion("Descripcion del proyecto dos");
        proyectoDos.setImagen("urldeimagenproyectodos.com");
        proyectoDos.setIdPropietario(1L);
        proyectoDos = proyectoRepository.save(proyectoDos);

        List<Proyecto> proyectosCreados = new ArrayList<>();
        proyectosCreados.add(proyectoUno);
        proyectosCreados.add(proyectoDos);

        List<Proyecto> proyectosGuardados = proyectoService.verTodos();

        assertArrayEquals(new List[]{proyectosCreados}, new List[]{proyectosGuardados});
    }
}
