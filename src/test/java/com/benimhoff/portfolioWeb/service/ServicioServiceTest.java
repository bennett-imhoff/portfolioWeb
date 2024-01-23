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
        Servicio servicio = crearServicioValido();
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
        Servicio servicio = crearServicioValido();
        servicio.setIdPropietario(null);

        assertThrows(IllegalArgumentException.class, ()->{
            servicioService.crear(servicio);
        });
    }

    @Test
    public void actualizar_conDatosValidos_actualizaServicio(){
        Servicio servicioAntiguo = crearServicioValido();
        servicioAntiguo = servicioRepository.save(servicioAntiguo);

        Servicio servicioNuevo = crearServicioValido();
        servicioNuevo.setId(servicioAntiguo.getId());
        servicioNuevo.setTitulo("Servicio nuevo");
        servicioService.actualizar(servicioNuevo);

        Servicio servicioGuardado = servicioRepository.findById(servicioAntiguo.getId()).get();

        assertEquals(servicioAntiguo.getId(), servicioNuevo.getId());
        assertEquals("Servicio nuevo", servicioGuardado.getTitulo());
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        Servicio servicioAntiguo = crearServicioValido();
        servicioAntiguo = servicioRepository.save(servicioAntiguo);

        Servicio servicioNuevo = crearServicioValido();
        servicioNuevo.setId(servicioAntiguo.getId());
        servicioNuevo.setIcono(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            servicioService.actualizar(servicioNuevo);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaServicio(){
        Servicio servicio = crearServicioValido();
        servicio = servicioRepository.save(servicio);

        servicioService.eliminar(servicio.getId());

        assertFalse(servicioRepository.existsById(servicio.getId()));
    }

    @Test
    public void verTodos_conCualquierUsuario_retornaListaServicios(){
        Servicio servicioUno = crearServicioValido();
        servicioUno = servicioRepository.save(servicioUno);

        Servicio servicioDos = crearServicioValido();
        servicioDos = servicioRepository.save(servicioDos);

        List<Servicio> serviciosCreados = new ArrayList<>();
        serviciosCreados.add(servicioUno);
        serviciosCreados.add(servicioDos);

        List<Servicio> serviciosGuardados = servicioService.verTodos();

        assertArrayEquals(new List[]{serviciosCreados}, new List[]{serviciosGuardados});
    }

    private Servicio crearServicioValido(){
        Servicio servicio = new Servicio();
        servicio.setIcono("fa-servicio");
        servicio.setTitulo("Servicio");
        servicio.setDescripcion("Descripción del servicio.");
        servicio.setIdPropietario(1L);

        return servicio;
    }
}
