package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.ExperienciaLaboral;
import com.benimhoff.portfolioWeb.repository.ExperienciaLaboralRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class ExperienciaLaboralServiceTest {

    @Autowired
    private ExperienciaLaboralService experienciaLaboralService;

    @Autowired
    private ExperienciaLaboralRepository experienciaLaboralRepository;

    @Test
    public void crear_conDatosValidos_guardaExperienciaLaboral(){
        ExperienciaLaboral experienciaLaboral = crearExperienciaLaboralValida();

        experienciaLaboral = experienciaLaboralService.crear(experienciaLaboral);

        assertNotNull(experienciaLaboral.getId());
    }

    @Test
    public void crear_conCamposEnBlanco_lanzaExcepcion(){
        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
        experienciaLaboral.setPuesto(" ");
        experienciaLaboral.setEmpresa(" ");
        experienciaLaboral.setDescripcion(" ");
        experienciaLaboral.setFechaInicio(new Date());
        experienciaLaboral.setIcono(" ");

        assertThrows(IllegalArgumentException.class, ()-> experienciaLaboralService.crear(experienciaLaboral));
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
        experienciaLaboral.setPuesto(null);
        experienciaLaboral.setEmpresa(null);
        experienciaLaboral.setDescripcion(null);
        experienciaLaboral.setFechaInicio(null);
        experienciaLaboral.setIcono(null);

        assertThrows(IllegalArgumentException.class, ()-> experienciaLaboralService.crear(experienciaLaboral));
    }

    @Test
    public void crear_conIdPropietarionulo_lanzaExcepcion(){
        ExperienciaLaboral experienciaLaboral = crearExperienciaLaboralValida();
        experienciaLaboral.setIdPropietario(null);

        assertThrows(IllegalArgumentException.class, ()-> experienciaLaboralService.crear(experienciaLaboral));
    }

    @Test
    public void actualizar_conDatosValidos_actualizaExperienciaLaboral(){
        ExperienciaLaboral experienciaLaboralAntigua = crearExperienciaLaboralValida();
        experienciaLaboralAntigua = experienciaLaboralRepository.save(experienciaLaboralAntigua);

        ExperienciaLaboral experienciaLaboralNueva = crearExperienciaLaboralValida();
        experienciaLaboralNueva.setId(experienciaLaboralAntigua.getId());
        experienciaLaboralNueva.setEmpresa("Nueva empresa");
        experienciaLaboralService.actualizar(experienciaLaboralNueva);

        ExperienciaLaboral experienciaLaboralGuardada = experienciaLaboralRepository.findById(experienciaLaboralAntigua.getId()).get();

        assertEquals(experienciaLaboralAntigua.getId(), experienciaLaboralGuardada.getId());
        assertEquals("Nueva empresa", experienciaLaboralGuardada.getEmpresa());
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        ExperienciaLaboral experienciaLaboralAntigua = crearExperienciaLaboralValida();
        experienciaLaboralAntigua = experienciaLaboralRepository.save(experienciaLaboralAntigua);

        ExperienciaLaboral experienciaLaboralNueva = crearExperienciaLaboralValida();
        experienciaLaboralNueva.setId(experienciaLaboralAntigua.getId());
        experienciaLaboralNueva.setEmpresa(" ");

        assertThrows(IllegalArgumentException.class, ()-> experienciaLaboralService.actualizar(experienciaLaboralNueva));
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaExperienciaLaboral(){
        ExperienciaLaboral experienciaLaboral = crearExperienciaLaboralValida();
        experienciaLaboral = experienciaLaboralRepository.save(experienciaLaboral);

        experienciaLaboralService.eliminar(experienciaLaboral.getId());

        assertFalse(experienciaLaboralRepository.existsById(experienciaLaboral.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaExperienciasLaborales(){
        ExperienciaLaboral experienciaUno = crearExperienciaLaboralValida();
        experienciaUno = experienciaLaboralRepository.save(experienciaUno);
        ExperienciaLaboral experienciaDos = crearExperienciaLaboralValida();
        experienciaDos = experienciaLaboralRepository.save(experienciaDos);

        List<ExperienciaLaboral> listaExperiencias = new ArrayList<>();
        listaExperiencias.add(experienciaUno);
        listaExperiencias.add(experienciaDos);

        List<ExperienciaLaboral> listaGuardadas = experienciaLaboralService.verTodas();

        assertArrayEquals(new List[]{listaExperiencias}, new List[]{listaGuardadas});
    }

    private ExperienciaLaboral crearExperienciaLaboralValida(){
        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
        experienciaLaboral.setPuesto("Desarrollador");
        experienciaLaboral.setEmpresa("Empresa");
        experienciaLaboral.setDescripcion("Trabajo como desarrollador.");
        experienciaLaboral.setFechaInicio(new Date());
        experienciaLaboral.setIcono("fa-icono");
        experienciaLaboral.setIdPropietario(1L);

        return experienciaLaboral;
    }
}
