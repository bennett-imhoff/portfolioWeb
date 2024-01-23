package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Habilidad;
import com.benimhoff.portfolioWeb.repository.HabilidadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HabilidadServiceTest {

    @Autowired
    private HabilidadService habilidadService;

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Test
    public void crear_conDatosValidos_guardaHabilidad(){
        Habilidad habilidad = crearHabilidadValida();
        habilidad = habilidadService.crear(habilidad);

        assertNotNull(habilidad.getId());
    }

    @Test
    public void crear_conCamposEnBlanco_lanzaExcepcion(){
        Habilidad habilidad = new Habilidad();
        habilidad.setNombre(" ");
        habilidad.setIdHabilidadSubcategoria(1L);

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadService.crear(habilidad);
        });
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        Habilidad habilidad = new Habilidad();
        habilidad.setNombre("Habilidad");
        habilidad.setIdHabilidadSubcategoria(null);

        assertThrows(IllegalArgumentException.class,()->{
           habilidadService.crear(habilidad);
        });
    }

    @Test
    public void actualizar_conCamposValidos_actualizaHabilidad(){
        Habilidad habilidadVieja = crearHabilidadValida();
        habilidadVieja = habilidadRepository.save(habilidadVieja);

        Habilidad habilidadNueva = crearHabilidadValida();
        habilidadNueva.setId(habilidadVieja.getId());
        habilidadNueva.setNombre("Habilidad nueva");
        habilidadService.actualizar(habilidadNueva);

        Habilidad habilidadGuardada = habilidadRepository.findById(habilidadVieja.getId()).get();

        assertEquals(habilidadVieja.getId(), habilidadGuardada.getId());
        assertEquals("Habilidad nueva", habilidadGuardada.getNombre());
    }

    @Test
    public void actualizar_conCamposInvalidos_lanzaExcepcion(){
        Habilidad habilidadVieja = crearHabilidadValida();
        habilidadVieja = habilidadRepository.save(habilidadVieja);

        Habilidad habilidadNueva = crearHabilidadValida();
        habilidadNueva.setId(habilidadVieja.getId());
        habilidadNueva.setNombre(" ");

        assertThrows(IllegalArgumentException.class, ()->{
           habilidadService.actualizar(habilidadNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaHabilidad(){
        Habilidad habilidad = crearHabilidadValida();
        habilidadRepository.save(habilidad);

        habilidadService.eliminar(habilidad.getId());

        assertFalse(habilidadRepository.existsById(habilidad.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaHabilidades(){
        Habilidad habilidadUno = crearHabilidadValida();
        habilidadUno = habilidadRepository.save(habilidadUno);

        Habilidad habilidadDos = crearHabilidadValida();
        habilidadDos = habilidadRepository.save(habilidadDos);

        List<Habilidad> habilidadesCreadas = new ArrayList<>();
        habilidadesCreadas.add(habilidadUno);
        habilidadesCreadas.add(habilidadDos);

        List<Habilidad> habilidadesGuardadas = habilidadService.verTodas();

        assertArrayEquals(new List[]{habilidadesCreadas}, new List[]{habilidadesGuardadas});
    }

    private Habilidad crearHabilidadValida(){
        Habilidad habilidad = new Habilidad();
        habilidad.setNombre("Habilidad");
        habilidad.setIdHabilidadSubcategoria(1L);

        return habilidad;
    }
}
