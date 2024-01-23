package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.RedSocial;
import com.benimhoff.portfolioWeb.repository.RedSocialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RedSocialServiceTest {

    @Autowired
    private RedSocialService redSocialService;

    @Autowired
    private RedSocialRepository redSocialRepository;

    @Test
    public void crear_conDatosValidos_guardaRedSocial(){
        RedSocial redSocial = crearRedSocialValida();
        redSocial = redSocialService.crear(redSocial);

        assertNotNull(redSocial.getId());
    }

    @Test
    public void crear_conCamposEnBlanco_lanzaExcepcion(){
        RedSocial redSocial = new RedSocial();
        redSocial.setNombre(" ");
        redSocial.setEnlace(" ");
        redSocial.setIcono(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            redSocialService.crear(redSocial);
        });
    }

    @Test
    public void crear_conCamposNulos_lanzaExcepcion(){
        RedSocial redSocial = new RedSocial();
        redSocial.setNombre(null);
        redSocial.setEnlace(null);
        redSocial.setIcono(null);

        assertThrows(IllegalArgumentException.class, ()->{
            redSocialService.crear(redSocial);
        });
    }

    @Test
    public void crear_conIdPropietarioNulo_lanzaExcepcion(){
        RedSocial redSocial = crearRedSocialValida();
        redSocial.setIdPropietario(null);

        assertThrows(IllegalArgumentException.class, ()->{
            redSocialService.crear(redSocial);
        });
    }

    @Test
    public void actualizar_conDatosValidos_actualizaRedSocial(){
        RedSocial redSocialAntigua = crearRedSocialValida();
        redSocialAntigua = redSocialRepository.save(redSocialAntigua);

        RedSocial redSocialNueva = crearRedSocialValida();
        redSocialNueva.setId(redSocialAntigua.getId());
        redSocialNueva.setNombre("Red social nueva");
        redSocialService.actualizar(redSocialNueva);

        RedSocial redSocialGuardada = redSocialRepository.findById(redSocialAntigua.getId()).get();

        assertEquals(redSocialAntigua.getId(), redSocialNueva.getId());
        assertEquals("Red social nueva", redSocialGuardada.getNombre());
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        RedSocial redSocialAntigua = crearRedSocialValida();
        redSocialAntigua = redSocialRepository.save(redSocialAntigua);

        RedSocial redSocialNueva = crearRedSocialValida();
        redSocialNueva.setId(redSocialAntigua.getId());
        redSocialNueva.setNombre(" ");

        assertThrows(IllegalArgumentException.class, ()->{
           redSocialService.actualizar(redSocialNueva);
        });
    }

    @Test
    public void eliminar_conUsuarioLogueado_eliminaRedSocial(){
        RedSocial redSocial = crearRedSocialValida();
        redSocial = redSocialRepository.save(redSocial);

        redSocialService.eliminar(redSocial.getId());

        assertFalse(redSocialRepository.existsById(redSocial.getId()));
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaRedesSociales(){
        RedSocial redSocialUno = crearRedSocialValida();
        redSocialUno = redSocialRepository.save(redSocialUno);

        RedSocial redSocialDos = crearRedSocialValida();
        redSocialDos = redSocialRepository.save(redSocialDos);

        List<RedSocial> redesCreadas = new ArrayList<>();
        redesCreadas.add(redSocialUno);
        redesCreadas.add(redSocialDos);

        List<RedSocial> redesGuardadas = redSocialService.verTodas();

        assertArrayEquals(new List[]{redesCreadas}, new List[]{redesGuardadas});
    }

    private RedSocial crearRedSocialValida(){
        RedSocial redSocial = new RedSocial();
        redSocial.setNombre("Red social");
        redSocial.setEnlace("redsocial.com");
        redSocial.setIcono("img.imagendeicono.com");
        redSocial.setIdPropietario(1L);

        return redSocial;
    }
}
