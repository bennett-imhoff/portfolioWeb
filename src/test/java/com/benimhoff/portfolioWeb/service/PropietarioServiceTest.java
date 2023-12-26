package com.benimhoff.portfolioWeb.service;

import com.benimhoff.portfolioWeb.domain.Propietario;
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
    public void guardar_conCamposValidos_guardaPropietario(){
        Propietario propietario = new Propietario();
        propietario.setNombre("Nombre");
        propietario.setApellido("Apellido");
        propietario.setDescripcion("Descripcion");
        propietario.setUsername("Username");
        propietario.setPassword("Password");

        Propietario propietarioGuardado = propietarioService.guardar(propietario);

        assertNotNull(propietarioGuardado.getId());
    }

    @Test
    public void guardar_conOtrosCamposValidos_actualizaPropietario(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre("Nombre");
        propietarioGuardado.setApellido("Apellido");
        propietarioGuardado.setDescripcion("Descripcion");
        propietarioGuardado.setUsername("Username");
        propietarioGuardado.setPassword("Password");
        propietarioGuardado = propietarioService.guardar(propietarioGuardado);

        Propietario propietarioModificado = new Propietario();
        propietarioModificado.setNombre("Lionel");
        propietarioModificado.setApellido("Messi");
        propietarioModificado.setDescripcion("Futbolista");
        propietarioModificado.setUsername("liomessi");
        propietarioModificado.setPassword("lio4ever");
        propietarioModificado = propietarioService.guardar(propietarioModificado);

        assertEquals(propietarioGuardado.getId(), propietarioModificado.getId());
    }

    @Test
    public void guardar_conCamposObligatoriosEnBlanco_lanzaExcepcion(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre(" ");
        propietarioGuardado.setApellido(" ");
        propietarioGuardado.setDescripcion(" ");
        propietarioGuardado.setUsername(" ");
        propietarioGuardado.setPassword(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            propietarioService.guardar(propietarioGuardado);
        });
    }

    @Test
    public void guardar_conCamposObligatoriosNulos_lanzaExcepcion(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre(null);
        propietarioGuardado.setApellido(null);
        propietarioGuardado.setDescripcion(null);
        propietarioGuardado.setUsername(null);
        propietarioGuardado.setPassword(null);

        assertThrows(IllegalArgumentException.class, ()->{
            propietarioService.guardar(propietarioGuardado);
        });
    }

    @Test
    public void esUsuarioValido_conCamposValidos_retornaTrue(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre("Nombre");
        propietarioGuardado.setApellido("Apellido");
        propietarioGuardado.setDescripcion("Descripcion");
        propietarioGuardado.setUsername("Username");
        propietarioGuardado.setPassword("Password");
        propietarioRepository.save(propietarioGuardado);

        String username = "Username";
        String password = "Password";

        propietarioService.esUsuarioValido(username, password);

        assertTrue(propietarioService.esUsuarioValido(username, password));
    }

    @Test
    public void esUsuarioValido_conCamposIncorrectos_lanzaExcepcion(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre("Nombre");
        propietarioGuardado.setApellido("Apellido");
        propietarioGuardado.setDescripcion("Descripcion");
        propietarioGuardado.setUsername("Username");
        propietarioGuardado.setPassword("Password");
        propietarioRepository.save(propietarioGuardado);

        String username = "incorrecto";
        String password = "incorrecta";

        assertThrows(IllegalArgumentException.class, ()->{
            propietarioService.esUsuarioValido(username, password);
        });
    }
}
