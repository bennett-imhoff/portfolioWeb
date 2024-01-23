package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Servicio;
import com.benimhoff.portfolioWeb.repository.ServicioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServicioServiceTest {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ServicioRepository servicioRepository;

    @Test
    public void crear_conDatosValidos_guardaServicio(){
        Servicio servicio = new Servicio();
        servicio.setIcono("fa-servicio");
        servicio.setTitulo("Servicio");
        servicio.setDescripcion("Descripción del servicio.");
        servicio.setIdPropietario(1L);
        servicio = servicioService.crear(servicio);

        assertNotNull(servicio.getId());
    }

    @Test
    public void crear_conCamposEnBlanco_lanzaExcepcion(){
        Servicio servicio = new Servicio();
        servicio.setIcono(" ");
        servicio.setTitulo(" ");
        servicio.setDescripcion(" ");
        servicio.setIdPropietario(1L);

        assertThrows(IllegalArgumentException.class, ()->{
           servicioService.crear(servicio);
        });
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        Servicio servicio = new Servicio();
        servicio.setIcono(null);
        servicio.setTitulo(null);
        servicio.setDescripcion(null);
        servicio.setIdPropietario(1L);

        assertThrows(IllegalArgumentException.class, ()->{
            servicioService.crear(servicio);
        });
    }

    @Test
    public void crear_conIdPropietarioNulo_lanzaExcepcion(){
        Servicio servicio = new Servicio();
        servicio.setIcono("fa-servicio");
        servicio.setTitulo("Servicio");
        servicio.setDescripcion("Descripción del servicio.");

        assertThrows(IllegalArgumentException.class, ()->{
            servicioService.crear(servicio);
        });
    }

    @Test
    public void actualizar_conDatosValidos_actualizaServicio(){
        Servicio servicioAntiguo = new Servicio();
        servicioAntiguo.setIcono("fa-servicio");
        servicioAntiguo.setTitulo("Servicio");
        servicioAntiguo.setDescripcion("Descripción del servicio.");
        servicioAntiguo.setIdPropietario(1L);
        servicioAntiguo = servicioRepository.save(servicioAntiguo);

        Servicio servicioNuevo = new Servicio();
        servicioNuevo.setId(servicioAntiguo.getId());
        servicioNuevo.setIcono("fa-servicio-nuevo");
        servicioNuevo.setTitulo("Servicio nuevo");
        servicioNuevo.setDescripcion("Descripción del servicio nuevo.");
        servicioService.actualizar(servicioNuevo);

        Servicio servicioGuardado = servicioRepository.findById(servicioAntiguo.getId()).get();

        assertEquals(servicioAntiguo.getId(), servicioNuevo.getId());
        assertAll("Verifico que todos los datos se actualizaron.",
            ()-> assertEquals("fa-servicio-nuevo", servicioGuardado.getIcono()),
            ()-> assertEquals("Servicio nuevo", servicioGuardado.getTitulo()),
            ()-> assertEquals("Descripción del servicio nuevo.", servicioGuardado.getDescripcion())
        );
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        Servicio servicioAntiguo = new Servicio();
        servicioAntiguo.setIcono("fa-servicio");
        servicioAntiguo.setTitulo("Servicio");
        servicioAntiguo.setDescripcion("Descripción del servicio.");
        servicioAntiguo.setIdPropietario(1L);
        servicioAntiguo = servicioRepository.save(servicioAntiguo);

        Servicio servicioNuevo = servicioRepository.findById(servicioAntiguo.getId()).get();
        servicioNuevo.setIcono(" ");
        servicioNuevo.setTitulo(" ");
        servicioNuevo.setDescripcion(null);

        assertThrows(IllegalArgumentException.class, ()->{
            servicioService.actualizar(servicioNuevo);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaServicio(){
        Servicio servicio = new Servicio();
        servicio.setIcono("fa-servicio");
        servicio.setTitulo("Servicio");
        servicio.setDescripcion("Descripción del servicio.");
        servicio.setIdPropietario(1L);
        servicio = servicioRepository.save(servicio);

        servicioService.eliminar(servicio.getId());

        assertFalse(servicioRepository.existsById(servicio.getId()));
    }

    @Test
    public void verTodos_conCualquierUsuario_retornaListaServicios(){
        Servicio servicioUno = new Servicio();
        servicioUno.setIcono("fa-servicio-uno");
        servicioUno.setTitulo("Servicio uno");
        servicioUno.setDescripcion("Descripción del servicio uno.");
        servicioUno.setIdPropietario(1L);
        servicioUno = servicioRepository.save(servicioUno);

        Servicio servicioDos = new Servicio();
        servicioDos.setIcono("fa-servicio-dos");
        servicioDos.setTitulo("Servicio dos");
        servicioDos.setDescripcion("Descripción del servicio dos.");
        servicioDos.setIdPropietario(1L);
        servicioDos = servicioRepository.save(servicioDos);

        List<Servicio> serviciosCreados = new ArrayList<>();
        serviciosCreados.add(servicioUno);
        serviciosCreados.add(servicioDos);

        List<Servicio> serviciosGuardados = servicioService.verTodos();

        assertArrayEquals(new List[]{serviciosCreados}, new List[]{serviciosGuardados});
    }
}
