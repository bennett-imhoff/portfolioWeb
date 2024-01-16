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
        Habilidad habilidad = new Habilidad();
        habilidad.setNombre("Habilidad");
        habilidad.setIdHabilidadSubcategoria(1L);
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
        Habilidad habilidadVieja = new Habilidad();
        habilidadVieja.setNombre("Habilidad vieja");
        habilidadVieja.setIdHabilidadSubcategoria(1L);
        habilidadVieja = habilidadRepository.save(habilidadVieja);

        Habilidad habilidadNueva = habilidadRepository.findById(habilidadVieja.getId()).get();
        habilidadNueva.setNombre("Habilidad nueva");
        habilidadNueva.setIdHabilidadSubcategoria(2L);
        habilidadService.actualizar(habilidadNueva);

        Habilidad habilidadGuardada = habilidadRepository.findById(habilidadVieja.getId()).get();

        assertEquals(habilidadVieja.getId(), habilidadGuardada.getId());
        assertAll("Verifico los datos actualizados.",
            ()-> assertEquals("Habilidad nueva", habilidadGuardada.getNombre()),
            ()-> assertEquals(2L, habilidadGuardada.getIdHabilidadSubcategoria())
        );
    }

    @Test
    public void actualizar_conCamposInvalidos_lanzaExcepcion(){
        Habilidad habilidadVieja = new Habilidad();
        habilidadVieja.setNombre("Habilidad");
        habilidadVieja.setIdHabilidadSubcategoria(1L);
        habilidadVieja = habilidadRepository.save(habilidadVieja);

        Habilidad habilidadNueva = habilidadRepository.findById(habilidadVieja.getId()).get();
        habilidadNueva.setNombre(" ");
        habilidadNueva.setIdHabilidadSubcategoria(null);

        assertThrows(IllegalArgumentException.class, ()->{
           habilidadService.actualizar(habilidadNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaHabilidad(){
        Habilidad habilidad = new Habilidad();
        habilidad.setNombre("Nombre");
        habilidad.setIdHabilidadSubcategoria(1L);
        habilidadRepository.save(habilidad);

        habilidadService.eliminar(habilidad.getId());

        assertFalse(habilidadRepository.existsById(habilidad.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaHabilidades(){
        Habilidad habilidadUno = new Habilidad();
        habilidadUno.setNombre("Nombre uno");
        habilidadUno.setIdHabilidadSubcategoria(1L);
        habilidadUno = habilidadRepository.save(habilidadUno);

        Habilidad habilidadDos = new Habilidad();
        habilidadDos.setNombre("Nombre dos");
        habilidadDos.setIdHabilidadSubcategoria(2L);
        habilidadDos = habilidadRepository.save(habilidadDos);

        List<Habilidad> habilidadesCreadas = new ArrayList<>();
        habilidadesCreadas.add(habilidadUno);
        habilidadesCreadas.add(habilidadDos);

        List<Habilidad> habilidadesGuardadas = habilidadService.verTodas();

        assertArrayEquals(new List[]{habilidadesCreadas}, new List[]{habilidadesGuardadas});
    }
}
