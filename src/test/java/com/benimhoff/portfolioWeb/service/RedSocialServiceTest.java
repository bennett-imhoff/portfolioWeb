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
    RedSocialService redSocialService;

    @Autowired
    RedSocialRepository redSocialRepository;

    @Test
    public void crear_conDatosValidos_guardaRedSocial(){
        RedSocial redSocial = new RedSocial();
        redSocial.setNombre("Red social");
        redSocial.setEnlace("redsocial.com");
        redSocial.setIcono("img.imagendeicono.com");
        redSocial.setIdPropietario(1L);
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
        RedSocial redSocial = new RedSocial();
        redSocial.setNombre("Red social");
        redSocial.setEnlace("redsocial.com");
        redSocial.setIcono("img.imagendeicono.com");

        assertThrows(IllegalArgumentException.class, ()->{
            redSocialService.crear(redSocial);
        });
    }

    @Test
    public void actualizar_conDatosValidos_actualizaRedSocial(){
        RedSocial redSocialAntigua = new RedSocial();
        redSocialAntigua.setNombre("Red social");
        redSocialAntigua.setEnlace("redsocial.com");
        redSocialAntigua.setIcono("fa-red-social");
        redSocialAntigua = redSocialRepository.save(redSocialAntigua);

        RedSocial redSocialNueva = redSocialRepository.findById(redSocialAntigua.getId()).get();
        redSocialNueva.setNombre("Red social nueva");
        redSocialNueva.setEnlace("redsocialnueva.com");
        redSocialNueva.setIcono("fa-red-social-nueva");

        redSocialNueva = redSocialService.actualizar(redSocialNueva);

        assertEquals(redSocialAntigua.getId(), redSocialNueva.getId());
    }

    @Test
    public void actualizar_conDatosInvalidos_lanzaExcepcion(){
        RedSocial redSocialAntigua = new RedSocial();
        redSocialAntigua.setNombre("Red social");
        redSocialAntigua.setEnlace("redsocial.com");
        redSocialAntigua.setIcono("fa-red-social");
        redSocialAntigua = redSocialRepository.save(redSocialAntigua);

        RedSocial redSocialNueva = redSocialRepository.findById(redSocialAntigua.getId()).get();
        redSocialNueva.setNombre(" ");
        redSocialNueva.setEnlace(" ");
        redSocialNueva.setIcono(null);

        assertThrows(IllegalArgumentException.class, ()->{
           redSocialService.actualizar(redSocialNueva);
        });
    }

    @Test
    public void verTodas_conCualquierUsuario_retornaListaRedesSociales(){
        RedSocial redSocialUno = new RedSocial();
        redSocialUno.setNombre("Red social uno");
        redSocialUno.setEnlace("redsocialuno.com");
        redSocialUno.setIcono("img.imagenderedsocialuno.com");
        redSocialUno.setIdPropietario(1L);
        redSocialUno = redSocialRepository.save(redSocialUno);

        RedSocial redSocialDos = new RedSocial();
        redSocialDos.setNombre("Red social dos");
        redSocialDos.setEnlace("redsocialdos.com");
        redSocialDos.setIcono("img.imagenderedsocialdos.com");
        redSocialDos.setIdPropietario(1L);
        redSocialDos = redSocialRepository.save(redSocialDos);

        List<RedSocial> redesCreadas = new ArrayList<>();
        redesCreadas.add(redSocialUno);
        redesCreadas.add(redSocialDos);

        List<RedSocial> redesGuardadas = redSocialService.verTodas();

        assertArrayEquals(new List[]{redesCreadas}, new List[]{redesGuardadas});
    }
}
