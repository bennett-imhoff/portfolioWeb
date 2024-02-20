package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Educacion;
import com.benimhoff.portfolioWeb.repository.EducacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EducacionServiceTest {

    @Autowired
    private EducacionService educacionService;

    @Autowired
    private EducacionRepository educacionRepository;

    @Test
    public void crear_conCamposValidos_guardaEducacion(){
        Educacion educacion = crearEducacionValida();
        educacion = educacionService.crear(educacion);

        assertNotNull(educacion.getId());
    }

    @Test
    public void crear_conCampoObligatorioEnBlanco_lanzaExcepcion(){
        Educacion educacion = crearEducacionValida();
        educacion.setTitulo(" ");

        assertThrows(IllegalArgumentException.class, ()-> educacionService.crear(educacion));
    }

    @Test
    public void crear_conFechaInicioNula_lanzaExcepcion(){
        Educacion educacion = crearEducacionValida();
        educacion.setFechaInicio(null);

        assertThrows(IllegalArgumentException.class, ()-> educacionService.crear(educacion));
    }

    @Test
    public void crear_conIdPropietarioNulo_lanzaExcepcion(){
        Educacion educacion = crearEducacionValida();
        educacion.setIdPropietario(null);

        assertThrows(IllegalArgumentException.class, ()-> educacionService.crear(educacion));
    }

    @Test
    public void actualizar_conDatosValidos_actualizaEducacion(){
        Educacion educacionVieja = crearEducacionValida();
        educacionVieja = educacionRepository.save(educacionVieja);

        Educacion educacionNueva = crearEducacionValida();
        educacionNueva.setId(educacionVieja.getId());
        educacionNueva.setTitulo("Titulo nuevo");
        educacionService.actualizar(educacionNueva);

        Educacion educacionGuardada = educacionRepository.findById(educacionVieja.getId()).get();

        assertEquals(educacionVieja.getId(), educacionGuardada.getId());
        assertEquals("Titulo nuevo", educacionGuardada.getTitulo());
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        Educacion educacionVieja = crearEducacionValida();
        educacionVieja = educacionRepository.save(educacionVieja);

        Educacion educacionNueva = crearEducacionValida();
        educacionNueva.setId(educacionVieja.getId());
        educacionNueva.setTitulo(" ");

        assertThrows(IllegalArgumentException.class,()-> educacionService.actualizar(educacionNueva));
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaEducacion(){
        Educacion educacion = crearEducacionValida();
        educacion = educacionRepository.save(educacion);

        educacionService.eliminar(educacion.getId());

        assertFalse(educacionRepository.existsById(educacion.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaEducacion(){
        Educacion educacionUno = crearEducacionValida();
        educacionUno = educacionRepository.save(educacionUno);
        Educacion educacionDos = crearEducacionValida();
        educacionDos = educacionRepository.save(educacionDos);

        List<Educacion> educacionesCreadas = new ArrayList<>();
        educacionesCreadas.add(educacionUno);
        educacionesCreadas.add(educacionDos);

        List<Educacion> educacionesGuardadas = educacionService.verTodas();

        assertArrayEquals(new List[]{educacionesCreadas}, new List[]{educacionesGuardadas});
    }

    private Educacion crearEducacionValida(){
        Educacion educacion = new Educacion();
        educacion.setTitulo("Titulo");
        educacion.setLugar("Lugar");
        educacion.setDescripcion("Descripcion");
        educacion.setFechaInicio(new Date());
        educacion.setIcono("fa-icono");
        educacion.setIdPropietario(1L);

        return educacion;
    }
}
