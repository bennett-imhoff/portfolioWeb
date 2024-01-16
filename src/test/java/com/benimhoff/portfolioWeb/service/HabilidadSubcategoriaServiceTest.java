package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.HabilidadSubcategoria;
import com.benimhoff.portfolioWeb.repository.HabilidadSubcategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HabilidadSubcategoriaServiceTest {

    @Autowired
    private HabilidadSubcategoriaService habilidadSubcategoriaService;

    @Autowired
    private HabilidadSubcategoriaRepository habilidadSubcategoriaRepository;

    @Test
    public void crear_conCamposValidos_guardaSubcategoria(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre("Subcategoria");
        subcategoria.setIdHabilidadCategoria(1L);
        subcategoria = habilidadSubcategoriaService.crear(subcategoria);

        assertNotNull(subcategoria.getId());
    }

    @Test
    public void crear_conNombreEnBlanco_lanzaExcepcion(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre(" ");
        subcategoria.setIdHabilidadCategoria(1L);

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadSubcategoriaService.crear(subcategoria);
        });
    }

    @Test
    public void crear_conIdCategoriaNulo_lanzaExcepcion(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre("Nombre");
        subcategoria.setIdHabilidadCategoria(null);

        assertThrows(IllegalArgumentException.class,()->{
            habilidadSubcategoriaService.crear(subcategoria);
        });
    }

    @Test
    public void actualizar_conCamposValidos_actualizaSubcategoria(){
        HabilidadSubcategoria subcategoriaVieja = new HabilidadSubcategoria();
        subcategoriaVieja.setNombre("Subcategoria vieja");
        subcategoriaVieja.setIdHabilidadCategoria(1L);
        subcategoriaVieja = habilidadSubcategoriaRepository.save(subcategoriaVieja);

        HabilidadSubcategoria subcategoriaNueva = habilidadSubcategoriaRepository.findById(subcategoriaVieja.getId()).get();
        subcategoriaNueva.setNombre("Subcategoria nueva");
        subcategoriaNueva.setIdHabilidadCategoria(2L);
        habilidadSubcategoriaService.actualizar(subcategoriaNueva);

        HabilidadSubcategoria subcategoriaGuardada = habilidadSubcategoriaRepository.findById(subcategoriaVieja.getId()).get();
        assertEquals(subcategoriaVieja.getId(), subcategoriaGuardada.getId());
        assertAll("Verifico que se actualicen los datos.",
            ()-> assertEquals("Subcategoria nueva", subcategoriaGuardada.getNombre()),
            ()-> assertEquals(2L, subcategoriaGuardada.getIdHabilidadCategoria())
        );
    }

    @Test
    public void actualizar_conCamposInvalidos_lanzaExcepcion(){
        HabilidadSubcategoria subcategoriaVieja = new HabilidadSubcategoria();
        subcategoriaVieja.setNombre("Nombre");
        subcategoriaVieja.setIdHabilidadCategoria(1L);
        subcategoriaVieja = habilidadSubcategoriaRepository.save(subcategoriaVieja);

        HabilidadSubcategoria subcategoriaNueva = habilidadSubcategoriaRepository.findById(subcategoriaVieja.getId()).get();
        subcategoriaNueva.setNombre(" ");
        subcategoriaNueva.setIdHabilidadCategoria(null);

        assertThrows(IllegalArgumentException.class, ()->{
           habilidadSubcategoriaService.actualizar(subcategoriaNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaSubcategoria(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre("Nombre");
        subcategoria.setIdHabilidadCategoria(1L);
        subcategoria = habilidadSubcategoriaRepository.save(subcategoria);

        habilidadSubcategoriaService.eliminar(subcategoria.getId());

        assertFalse(habilidadSubcategoriaRepository.existsById(subcategoria.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaSubcategorias(){
        HabilidadSubcategoria subcategoriaUno = new HabilidadSubcategoria();
        subcategoriaUno.setNombre("Nombre uno");
        subcategoriaUno.setIdHabilidadCategoria(1L);
        subcategoriaUno = habilidadSubcategoriaRepository.save(subcategoriaUno);

        HabilidadSubcategoria subcategoriaDos = new HabilidadSubcategoria();
        subcategoriaDos.setNombre("Nombre dos");
        subcategoriaDos.setIdHabilidadCategoria(2L);
        subcategoriaDos = habilidadSubcategoriaRepository.save(subcategoriaDos);

        List<HabilidadSubcategoria> subcategoriasCreadas = new ArrayList<>();
        subcategoriasCreadas.add(subcategoriaUno);
        subcategoriasCreadas.add(subcategoriaDos);

        List<HabilidadSubcategoria> subcategoriasGuardadas = habilidadSubcategoriaService.verTodas();

        assertArrayEquals(new List[]{subcategoriasCreadas}, new List[]{subcategoriasGuardadas});
    }
}
