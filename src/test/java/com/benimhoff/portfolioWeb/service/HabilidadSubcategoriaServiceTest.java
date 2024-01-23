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
        subcategoria.setNecesitaDescripcion(true);
        subcategoria.setNecesitaPorcentaje(false);
        subcategoria.setNecesitaIcono(true);
        subcategoria.setNecesitaImagen(false);
        subcategoria.setNecesitaNivel(true);
        subcategoria.setIdHabilidadCategoria(1L);
        subcategoria = habilidadSubcategoriaService.crear(subcategoria);

        assertNotNull(subcategoria.getId());
    }

    @Test
    public void crear_conNombreEnBlanco_lanzaExcepcion(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre(" ");
        subcategoria.setNecesitaDescripcion(true);
        subcategoria.setNecesitaPorcentaje(false);
        subcategoria.setNecesitaIcono(true);
        subcategoria.setNecesitaImagen(false);
        subcategoria.setNecesitaNivel(true);
        subcategoria.setIdHabilidadCategoria(1L);

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadSubcategoriaService.crear(subcategoria);
        });
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre("Nombre");
        subcategoria.setNecesitaDescripcion(null);
        subcategoria.setNecesitaPorcentaje(null);
        subcategoria.setNecesitaIcono(null);
        subcategoria.setNecesitaImagen(null);
        subcategoria.setNecesitaNivel(null);
        subcategoria.setIdHabilidadCategoria(null);

        assertThrows(IllegalArgumentException.class,()->{
            habilidadSubcategoriaService.crear(subcategoria);
        });
    }

    @Test
    public void actualizar_conCamposValidos_actualizaSubcategoria(){
        HabilidadSubcategoria subcategoriaVieja = new HabilidadSubcategoria();
        subcategoriaVieja.setNombre("Subcategoria vieja");
        subcategoriaVieja.setNecesitaDescripcion(true);
        subcategoriaVieja.setNecesitaPorcentaje(false);
        subcategoriaVieja.setNecesitaIcono(true);
        subcategoriaVieja.setNecesitaImagen(false);
        subcategoriaVieja.setNecesitaNivel(true);
        subcategoriaVieja.setIdHabilidadCategoria(1L);
        subcategoriaVieja = habilidadSubcategoriaRepository.save(subcategoriaVieja);

        HabilidadSubcategoria subcategoriaNueva = new HabilidadSubcategoria();
        subcategoriaNueva.setId(subcategoriaVieja.getId());
        subcategoriaNueva.setNombre("Subcategoria nueva");
        subcategoriaNueva.setNecesitaDescripcion(false);
        subcategoriaNueva.setNecesitaPorcentaje(true);
        subcategoriaNueva.setNecesitaIcono(false);
        subcategoriaNueva.setNecesitaImagen(true);
        subcategoriaNueva.setNecesitaNivel(false);
        subcategoriaNueva.setIdHabilidadCategoria(2L);
        habilidadSubcategoriaService.actualizar(subcategoriaNueva);

        HabilidadSubcategoria subcategoriaGuardada = habilidadSubcategoriaRepository.findById(subcategoriaVieja.getId()).get();
        assertEquals(subcategoriaVieja.getId(), subcategoriaGuardada.getId());
        assertAll("Verifico que se actualicen los datos.",
            ()-> assertEquals("Subcategoria nueva", subcategoriaGuardada.getNombre()),
            ()-> assertTrue(subcategoriaGuardada.isNecesitaPorcentaje()),
            ()->assertTrue(subcategoriaGuardada.isNecesitaImagen()),
            ()->assertFalse(subcategoriaGuardada.isNecesitaDescripcion()),
            ()->assertFalse(subcategoriaGuardada.isNecesitaIcono()),
            ()->assertFalse(subcategoriaGuardada.isNecesitaNivel()),
            ()-> assertEquals(2L, subcategoriaGuardada.getIdHabilidadCategoria())
        );
    }

    @Test
    public void actualizar_conCamposInvalidos_lanzaExcepcion(){
        HabilidadSubcategoria subcategoriaVieja = new HabilidadSubcategoria();
        subcategoriaVieja.setNombre("Nombre");
        subcategoriaVieja.setNecesitaDescripcion(true);
        subcategoriaVieja.setNecesitaPorcentaje(false);
        subcategoriaVieja.setNecesitaIcono(true);
        subcategoriaVieja.setNecesitaImagen(false);
        subcategoriaVieja.setNecesitaNivel(true);
        subcategoriaVieja.setIdHabilidadCategoria(1L);
        subcategoriaVieja = habilidadSubcategoriaRepository.save(subcategoriaVieja);

        HabilidadSubcategoria subcategoriaNueva = habilidadSubcategoriaRepository.findById(subcategoriaVieja.getId()).get();
        subcategoriaNueva.setNombre(" ");
        subcategoriaVieja.setNecesitaDescripcion(null);
        subcategoriaVieja.setNecesitaPorcentaje(null);
        subcategoriaVieja.setNecesitaIcono(null);
        subcategoriaVieja.setNecesitaImagen(null);
        subcategoriaVieja.setNecesitaNivel(null);
        subcategoriaNueva.setIdHabilidadCategoria(null);

        assertThrows(IllegalArgumentException.class, ()->{
           habilidadSubcategoriaService.actualizar(subcategoriaNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaSubcategoria(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre("Nombre");
        subcategoria.setNecesitaDescripcion(true);
        subcategoria.setNecesitaPorcentaje(false);
        subcategoria.setNecesitaIcono(true);
        subcategoria.setNecesitaImagen(false);
        subcategoria.setNecesitaNivel(true);
        subcategoria.setIdHabilidadCategoria(1L);
        subcategoria = habilidadSubcategoriaRepository.save(subcategoria);

        habilidadSubcategoriaService.eliminar(subcategoria.getId());

        assertFalse(habilidadSubcategoriaRepository.existsById(subcategoria.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaSubcategorias(){
        HabilidadSubcategoria subcategoriaUno = new HabilidadSubcategoria();
        subcategoriaUno.setNombre("Nombre uno");
        subcategoriaUno.setNecesitaDescripcion(true);
        subcategoriaUno.setNecesitaPorcentaje(false);
        subcategoriaUno.setNecesitaIcono(true);
        subcategoriaUno.setNecesitaImagen(false);
        subcategoriaUno.setNecesitaNivel(true);
        subcategoriaUno.setIdHabilidadCategoria(1L);
        subcategoriaUno = habilidadSubcategoriaRepository.save(subcategoriaUno);

        HabilidadSubcategoria subcategoriaDos = new HabilidadSubcategoria();
        subcategoriaDos.setNombre("Nombre dos");
        subcategoriaDos.setNecesitaDescripcion(true);
        subcategoriaDos.setNecesitaPorcentaje(false);
        subcategoriaDos.setNecesitaIcono(true);
        subcategoriaDos.setNecesitaImagen(false);
        subcategoriaDos.setNecesitaNivel(true);
        subcategoriaDos.setIdHabilidadCategoria(2L);
        subcategoriaDos = habilidadSubcategoriaRepository.save(subcategoriaDos);

        List<HabilidadSubcategoria> subcategoriasCreadas = new ArrayList<>();
        subcategoriasCreadas.add(subcategoriaUno);
        subcategoriasCreadas.add(subcategoriaDos);

        List<HabilidadSubcategoria> subcategoriasGuardadas = habilidadSubcategoriaService.verTodas();

        assertArrayEquals(new List[]{subcategoriasCreadas}, new List[]{subcategoriasGuardadas});
    }
}
