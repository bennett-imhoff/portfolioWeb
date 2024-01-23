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
        HabilidadCategoria habilidadCategoria = new HabilidadCategoria();
        habilidadCategoria.setNombre("Categoría");
        habilidadCategoria.setDescripcion("Esta es la categoría de las categorías.");
        habilidadCategoria.setIcono("fa fa-categoria");
        habilidadCategoria.setColorIcono("#712987");
        habilidadCategoria.setIdPropietario(1L);
        habilidadCategoria = habilidadCategoriaService.crear(habilidadCategoria);

        assertNotNull(habilidadCategoria.getId());
    }

    @Test
    public void crear_conIdPropietarioNulo_lanzaExcepcion(){
        HabilidadCategoria habilidadCategoria = new HabilidadCategoria();
        habilidadCategoria.setNombre("Categoría");
        habilidadCategoria.setDescripcion("Esta es la categoría de las categorías.");
        habilidadCategoria.setIcono("fa fa-categoria");
        habilidadCategoria.setColorIcono("#712987");

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
        HabilidadCategoria habilidadCategoriaAntigua = new HabilidadCategoria();
        habilidadCategoriaAntigua.setNombre("Categoría");
        habilidadCategoriaAntigua.setDescripcion("Esta es la categoría de las categorías.");
        habilidadCategoriaAntigua.setIcono("fa fa-categoria");
        habilidadCategoriaAntigua.setColorIcono("#712987");
        habilidadCategoriaAntigua.setIdPropietario(1L);
        habilidadCategoriaAntigua = habilidadCategoriaRepository.save(habilidadCategoriaAntigua);

        HabilidadCategoria habilidadCategoriaNueva = new HabilidadCategoria();
        habilidadCategoriaNueva.setId(habilidadCategoriaAntigua.getId());
        habilidadCategoriaNueva.setNombre("Nueva categoría");
        habilidadCategoriaNueva.setDescripcion("Nueva descripción.");
        habilidadCategoriaNueva.setIcono("fa fa-nueva");
        habilidadCategoriaNueva.setColorIcono("#7129872");
        habilidadCategoriaService.actualizar(habilidadCategoriaNueva);

        HabilidadCategoria habilidadCategoriaGuardada = habilidadCategoriaRepository.findById(habilidadCategoriaAntigua.getId()).get();

        assertEquals(habilidadCategoriaAntigua.getId(), habilidadCategoriaGuardada.getId());
        assertAll("Verifico que los datos se hayan actualizado.",
            ()-> assertEquals("Nueva categoría", habilidadCategoriaGuardada.getNombre()),
            ()-> assertEquals("Nueva descripción.", habilidadCategoriaGuardada.getDescripcion()),
            ()-> assertEquals("fa fa-nueva", habilidadCategoriaGuardada.getIcono()),
            ()-> assertEquals("#7129872", habilidadCategoriaGuardada.getColorIcono())
        );
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        HabilidadCategoria habilidadCategoriaAntigua = new HabilidadCategoria();
        habilidadCategoriaAntigua.setNombre("Categoría");
        habilidadCategoriaAntigua.setDescripcion("Esta es la categoría de las categorías.");
        habilidadCategoriaAntigua.setIcono("fa fa-categoria");
        habilidadCategoriaAntigua.setColorIcono("#712987");
        habilidadCategoriaAntigua.setIdPropietario(1L);
        habilidadCategoriaAntigua = habilidadCategoriaRepository.save(habilidadCategoriaAntigua);

        HabilidadCategoria habilidadCategoriaNueva = habilidadCategoriaRepository.findById(habilidadCategoriaAntigua.getId()).get();
        habilidadCategoriaNueva.setNombre(" ");
        habilidadCategoriaNueva.setDescripcion(" ");
        habilidadCategoriaNueva.setIcono(null);
        habilidadCategoriaNueva.setColorIcono(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            habilidadCategoriaService.actualizar(habilidadCategoriaNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaHabilidadCategoria(){
        HabilidadCategoria habilidadCategoria = new HabilidadCategoria();
        habilidadCategoria.setNombre("Categoría");
        habilidadCategoria.setDescripcion("Esta es la categoría de las categorías.");
        habilidadCategoria.setIcono("fa fa-categoria");
        habilidadCategoria.setColorIcono("#712987");
        habilidadCategoria.setIdPropietario(1L);
        habilidadCategoria = habilidadCategoriaRepository.save(habilidadCategoria);

        habilidadCategoriaService.eliminar(habilidadCategoria.getId());

        assertFalse(habilidadCategoriaRepository.existsById(habilidadCategoria.getId()));
    }

    @Test
    public void verTodos_conCualquierUsuario_retornaListaHabilidadCategoria(){
        HabilidadCategoria habilidadCategoria = new HabilidadCategoria();
        habilidadCategoria.setNombre("Categoría");
        habilidadCategoria.setDescripcion("Esta es la categoría de las categorías.");
        habilidadCategoria.setIcono("fa fa-categoria");
        habilidadCategoria.setColorIcono("#712987");
        habilidadCategoria.setIdPropietario(1L);
        habilidadCategoria = habilidadCategoriaRepository.save(habilidadCategoria);

        HabilidadCategoria habilidadCategoriaDos = new HabilidadCategoria();
        habilidadCategoriaDos.setNombre("Categoría 2");
        habilidadCategoriaDos.setDescripcion("Esta es la categoría 2 de las categorías 2.");
        habilidadCategoriaDos.setIcono("fa fa-categoria-2");
        habilidadCategoria.setColorIcono("#712982");
        habilidadCategoriaDos.setIdPropietario(1L);
        habilidadCategoriaDos = habilidadCategoriaRepository.save(habilidadCategoriaDos);

        List<HabilidadCategoria> categoriasCreadas = new ArrayList<>();
        categoriasCreadas.add(habilidadCategoria);
        categoriasCreadas.add(habilidadCategoriaDos);

        List<HabilidadCategoria> categoriasGuardadas = habilidadCategoriaService.verTodas();

        assertArrayEquals(new List[]{categoriasCreadas}, new List[]{categoriasGuardadas});
    }
}
