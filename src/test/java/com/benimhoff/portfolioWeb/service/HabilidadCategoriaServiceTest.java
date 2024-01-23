package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.HabilidadCategoria;
import com.benimhoff.portfolioWeb.repository.HabilidadCategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HabilidadCategoriaServiceTest {

    @Autowired
    private HabilidadCategoriaService habilidadCategoriaService;

    @Autowired
    private HabilidadCategoriaRepository habilidadCategoriaRepository;

    @Test
    public void crear_conDatosValidos_guardaHabilidadCategoria(){
        HabilidadCategoria habilidadCategoria = crearCategoriaValida();
        habilidadCategoria = habilidadCategoriaService.crear(habilidadCategoria);

        assertNotNull(habilidadCategoria.getId());
    }

    @Test
    public void crear_conIdPropietarioNulo_lanzaExcepcion(){
        HabilidadCategoria habilidadCategoria = crearCategoriaValida();
        habilidadCategoria.setIdPropietario(null);

        assertThrows(IllegalArgumentException.class, ()->{
           habilidadCategoriaService.crear(habilidadCategoria);
        });
    }

    @Test
    public void crear_conCamposEnBlanco_lanzaExcepcion(){
        HabilidadCategoria habilidadCategoria = new HabilidadCategoria();
        habilidadCategoria.setNombre(" ");
        habilidadCategoria.setDescripcion(" ");
        habilidadCategoria.setIcono(" ");
        habilidadCategoria.setColorIcono(" ");
        habilidadCategoria.setIdPropietario(1L);

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadCategoriaService.crear(habilidadCategoria);
        });
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        HabilidadCategoria habilidadCategoria = new HabilidadCategoria();
        habilidadCategoria.setNombre(null);
        habilidadCategoria.setDescripcion(null);
        habilidadCategoria.setIcono(null);
        habilidadCategoria.setColorIcono(null);
        habilidadCategoria.setIdPropietario(1L);

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadCategoriaService.crear(habilidadCategoria);
        });
    }

    @Test
    public void actualizar_conDatosValidos_actualizaHabilidadCategoria(){
        HabilidadCategoria habilidadCategoriaAntigua = crearCategoriaValida();
        habilidadCategoriaAntigua = habilidadCategoriaRepository.save(habilidadCategoriaAntigua);

        HabilidadCategoria habilidadCategoriaNueva = crearCategoriaValida();
        habilidadCategoriaNueva.setId(habilidadCategoriaAntigua.getId());
        habilidadCategoriaNueva.setNombre("Nueva categoría");
        habilidadCategoriaService.actualizar(habilidadCategoriaNueva);

        HabilidadCategoria habilidadCategoriaGuardada = habilidadCategoriaRepository.findById(habilidadCategoriaAntigua.getId()).get();

        assertEquals(habilidadCategoriaAntigua.getId(), habilidadCategoriaGuardada.getId());
        assertEquals("Nueva categoria", habilidadCategoriaGuardada.getNombre());
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        HabilidadCategoria habilidadCategoriaAntigua = crearCategoriaValida();
        habilidadCategoriaAntigua = habilidadCategoriaRepository.save(habilidadCategoriaAntigua);

        HabilidadCategoria habilidadCategoriaNueva = crearCategoriaValida();
        habilidadCategoriaNueva.setId(habilidadCategoriaAntigua.getId());
        habilidadCategoriaNueva.setNombre(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadCategoriaService.actualizar(habilidadCategoriaNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaHabilidadCategoria(){
        HabilidadCategoria habilidadCategoria = crearCategoriaValida();
        habilidadCategoria = habilidadCategoriaRepository.save(habilidadCategoria);

        habilidadCategoriaService.eliminar(habilidadCategoria.getId());

        assertFalse(habilidadCategoriaRepository.existsById(habilidadCategoria.getId()));
    }

    @Test
    public void verTodos_conCualquierUsuario_retornaListaHabilidadCategoria(){
        HabilidadCategoria habilidadCategoria = crearCategoriaValida();
        habilidadCategoria = habilidadCategoriaRepository.save(habilidadCategoria);

        HabilidadCategoria habilidadCategoriaDos = crearCategoriaValida();
        habilidadCategoriaDos = habilidadCategoriaRepository.save(habilidadCategoriaDos);

        List<HabilidadCategoria> categoriasCreadas = new ArrayList<>();
        categoriasCreadas.add(habilidadCategoria);
        categoriasCreadas.add(habilidadCategoriaDos);

        List<HabilidadCategoria> categoriasGuardadas = habilidadCategoriaService.verTodas();

        assertArrayEquals(new List[]{categoriasCreadas}, new List[]{categoriasGuardadas});
    }

    private HabilidadCategoria crearCategoriaValida(){
        HabilidadCategoria habilidadCategoria = new HabilidadCategoria();
        habilidadCategoria.setNombre("Categoría");
        habilidadCategoria.setDescripcion("Esta es la categoría de las categorías.");
        habilidadCategoria.setIcono("fa fa-categoria");
        habilidadCategoria.setColorIcono("#712987");
        habilidadCategoria.setIdPropietario(1L);

        return habilidadCategoria;
    }
}
