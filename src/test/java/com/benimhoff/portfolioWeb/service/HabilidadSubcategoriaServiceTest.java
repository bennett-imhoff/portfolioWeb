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
        HabilidadSubcategoria subcategoria = crearSubcategoriaValida();
        subcategoria = habilidadSubcategoriaService.crear(subcategoria);

        assertNotNull(subcategoria.getId());
    }

    @Test
    public void crear_conNombreEnBlanco_lanzaExcepcion(){
        HabilidadSubcategoria subcategoria = crearSubcategoriaValida();
        subcategoria.setNombre(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadSubcategoriaService.crear(subcategoria);
        });
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        HabilidadSubcategoria subcategoria = crearSubcategoriaValida();
        subcategoria.setNecesitaDescripcion(null);

        assertThrows(IllegalArgumentException.class,()->{
            habilidadSubcategoriaService.crear(subcategoria);
        });
    }

    @Test
    public void actualizar_conCamposValidos_actualizaSubcategoria(){
        HabilidadSubcategoria subcategoriaVieja = crearSubcategoriaValida();
        subcategoriaVieja = habilidadSubcategoriaRepository.save(subcategoriaVieja);

        HabilidadSubcategoria subcategoriaNueva = crearSubcategoriaValida();
        subcategoriaNueva.setId(subcategoriaVieja.getId());
        subcategoriaNueva.setNombre("Subcategoria nueva");
        habilidadSubcategoriaService.actualizar(subcategoriaNueva);

        HabilidadSubcategoria subcategoriaGuardada = habilidadSubcategoriaRepository.findById(subcategoriaVieja.getId()).get();

        assertEquals(subcategoriaVieja.getId(), subcategoriaGuardada.getId());
        assertEquals("Subcategoria nueva", subcategoriaGuardada.getNombre());
    }

    @Test
    public void actualizar_conCamposInvalidos_lanzaExcepcion(){
        HabilidadSubcategoria subcategoriaVieja = crearSubcategoriaValida();
        subcategoriaVieja = habilidadSubcategoriaRepository.save(subcategoriaVieja);

        HabilidadSubcategoria subcategoriaNueva = crearSubcategoriaValida();
        subcategoriaNueva.setNombre(" ");

        assertThrows(IllegalArgumentException.class, ()->{
           habilidadSubcategoriaService.actualizar(subcategoriaNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaSubcategoria(){
        HabilidadSubcategoria subcategoria = crearSubcategoriaValida();
        subcategoria = habilidadSubcategoriaRepository.save(subcategoria);

        habilidadSubcategoriaService.eliminar(subcategoria.getId());

        assertFalse(habilidadSubcategoriaRepository.existsById(subcategoria.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaSubcategorias(){
        HabilidadSubcategoria subcategoriaUno = crearSubcategoriaValida();
        subcategoriaUno = habilidadSubcategoriaRepository.save(subcategoriaUno);

        HabilidadSubcategoria subcategoriaDos = crearSubcategoriaValida();
        subcategoriaDos = habilidadSubcategoriaRepository.save(subcategoriaDos);

        List<HabilidadSubcategoria> subcategoriasCreadas = new ArrayList<>();
        subcategoriasCreadas.add(subcategoriaUno);
        subcategoriasCreadas.add(subcategoriaDos);

        List<HabilidadSubcategoria> subcategoriasGuardadas = habilidadSubcategoriaService.verTodas();

        assertArrayEquals(new List[]{subcategoriasCreadas}, new List[]{subcategoriasGuardadas});
    }

    private HabilidadSubcategoria crearSubcategoriaValida(){
        HabilidadSubcategoria subcategoria = new HabilidadSubcategoria();
        subcategoria.setNombre("Subcategoria");
        subcategoria.setNecesitaDescripcion(true);
        subcategoria.setNecesitaPorcentaje(false);
        subcategoria.setNecesitaIcono(true);
        subcategoria.setNecesitaImagen(false);
        subcategoria.setNecesitaNivel(true);
        subcategoria.setIdHabilidadCategoria(1L);

        return subcategoria;
    }
}
