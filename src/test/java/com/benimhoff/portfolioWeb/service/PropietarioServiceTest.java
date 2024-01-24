package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.exception.LoginException;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class PropietarioServiceTest {

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Test
    public void actualizar_conCamposValidos_actualizaPropietario(){
        Propietario propietarioSinActualizar = crearPropietarioValido();
        propietarioSinActualizar = propietarioRepository.save(propietarioSinActualizar);

        Propietario propietarioActualizado = crearPropietarioValido();
        propietarioActualizado.setId(propietarioSinActualizar.getId());
        propietarioActualizado.setNombre("Lionel");
        propietarioService.actualizar(propietarioActualizado);

        Propietario propietarioGuardado = propietarioRepository.findById(propietarioSinActualizar.getId()).get();

        assertEquals(propietarioSinActualizar.getId(), propietarioGuardado.getId());
        assertEquals("Lionel", propietarioGuardado.getNombre());
    }

    @Test
    public void actualizar_conCampoObligatorioEnBlanco_lanzaExcepcion(){
        Propietario propietarioSinActualizar = crearPropietarioValido();
        propietarioSinActualizar = propietarioRepository.save(propietarioSinActualizar);

        Propietario propietarioActualizado = crearPropietarioValido();
        propietarioActualizado.setId(propietarioSinActualizar.getId());
        propietarioActualizado.setNombre(" ");

        assertThrows(IllegalArgumentException.class, ()-> propietarioService.actualizar(propietarioActualizado));
    }

    @Test
    public void actualizar_conCampoObligatorioNulo_lanzaExcepcion(){
        Propietario propietarioSinActualizar = crearPropietarioValido();
        propietarioSinActualizar = propietarioRepository.save(propietarioSinActualizar);

        Propietario propietarioActualizado = crearPropietarioValido();
        propietarioActualizado.setId(propietarioSinActualizar.getId());
        propietarioActualizado.setUsername(null);

        assertThrows(IllegalArgumentException.class, ()-> propietarioService.actualizar(propietarioActualizado));
    }

    @Test
    public void esUsuarioValido_conCamposValidos_retornaTrue(){
        Propietario propietarioGuardado = crearPropietarioValido();
        propietarioRepository.save(propietarioGuardado);

        String username = "Username";
        String password = "Password";

        propietarioService.esUsuarioValido(username, password);

        assertTrue(propietarioService.esUsuarioValido(username, password));
    }

    @Test
    public void esUsuarioValido_conCamposIncorrectos_lanzaExcepcion(){
        Propietario propietarioGuardado = crearPropietarioValido();
        propietarioRepository.save(propietarioGuardado);

        String username = "incorrecto";
        String password = "incorrecta";

        assertThrows(LoginException.class, ()-> propietarioService.esUsuarioValido(username, password));
    }

    @Test
    public void ver_conCualquierUsuario_retornaPropietario(){
        Propietario propietarioGuardado = crearPropietarioValido();
        propietarioRepository.save(propietarioGuardado);

        Propietario propietarioRetornado = propietarioService.ver();

        assertEquals("Apellido", propietarioRetornado.getApellido());
    }

    private Propietario crearPropietarioValido(){
        Propietario propietario = new Propietario();
        propietario.setNombre("Nombre");
        propietario.setApellido("Apellido");
        propietario.setDescripcion("Descripcion");
        propietario.setUsername("Username");
        propietario.setPassword("Password");
        propietario.setTextoSoy("Soy");
        propietario.setTextoHago("Hago");
        propietario.setTextoQuiero("Quiero");

        return propietario;
    }

}
