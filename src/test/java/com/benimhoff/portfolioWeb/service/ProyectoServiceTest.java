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
        Proyecto proyecto = crearProyectoValido();

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

        assertThrows(IllegalArgumentException.class, ()-> proyectoService.crear(proyecto));
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo(null);
        proyecto.setResumen(null);
        proyecto.setDescripcion(null);
        proyecto.setImagen(null);

        assertThrows(IllegalArgumentException.class, ()-> proyectoService.crear(proyecto));
    }

    @Test
    public void crear_conIdPropietarioNulo_lanzaExcepcion(){
        Proyecto proyecto = crearProyectoValido();
        proyecto.setIdPropietario(null);

        assertThrows(IllegalArgumentException.class, ()-> proyectoService.crear(proyecto));
    }

    @Test
    public void actualizar_conDatosValidos_actualizaProyecto(){
        Proyecto proyectoAntiguo = crearProyectoValido();
        proyectoAntiguo = proyectoRepository.save(proyectoAntiguo);

        Proyecto proyectoNuevo = crearProyectoValido();
        proyectoNuevo.setId(proyectoAntiguo.getId());
        proyectoNuevo.setTitulo("Titulo del proyecto nuevo");
        proyectoService.actualizar(proyectoNuevo);

        Proyecto proyectoGuardado = proyectoRepository.findById(proyectoAntiguo.getId()).get();

        assertEquals(proyectoAntiguo.getId(), proyectoNuevo.getId());
        assertEquals("Titulo del proyecto nuevo", proyectoGuardado.getTitulo());
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        Proyecto proyectoAntiguo = crearProyectoValido();
        proyectoAntiguo = proyectoRepository.save(proyectoAntiguo);

        Proyecto proyectoNuevo = crearProyectoValido();
        proyectoNuevo.setId(proyectoAntiguo.getId());
        proyectoNuevo.setTitulo(" ");

        assertThrows(IllegalArgumentException.class, ()-> proyectoService.actualizar(proyectoNuevo));
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaProyecto(){
        Proyecto proyecto = crearProyectoValido();
        proyecto = proyectoRepository.save(proyecto);

        proyectoService.eliminar(proyecto.getId());

        assertFalse(proyectoRepository.existsById(proyecto.getId()));
    }

    @Test
    public void verTodos_conCualquierUsuario_retornaListaProyectos(){
        Proyecto proyectoUno = crearProyectoValido();
        proyectoUno = proyectoRepository.save(proyectoUno);

        Proyecto proyectoDos = crearProyectoValido();
        proyectoDos = proyectoRepository.save(proyectoDos);

        List<Proyecto> proyectosCreados = new ArrayList<>();
        proyectosCreados.add(proyectoUno);
        proyectosCreados.add(proyectoDos);

        List<Proyecto> proyectosGuardados = proyectoService.verTodos();

        assertArrayEquals(new List[]{proyectosCreados}, new List[]{proyectosGuardados});
    }

    private Proyecto crearProyectoValido(){
        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo("Titulo del proyecto");
        proyecto.setResumen("Resumen del proyecto");
        proyecto.setDescripcion("Descripcion del proyecto");
        proyecto.setImagen("urldeimagen.com");
        proyecto.setIdPropietario(1L);

        return proyecto;
    }
}
