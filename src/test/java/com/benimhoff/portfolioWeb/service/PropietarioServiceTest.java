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
    public void actualizar_conCamposValidos_actualizaPropietario(){
        Propietario propietarioSinActualizar = new Propietario();
        propietarioSinActualizar.setNombre("Nombre");
        propietarioSinActualizar.setApellido("Apellido");
        propietarioSinActualizar.setDescripcion("Descripcion");
        propietarioSinActualizar.setUsername("Username");
        propietarioSinActualizar.setPassword("Password");
        propietarioSinActualizar.setTextoSoy("Soy");
        propietarioSinActualizar.setTextoHago("Hago");
        propietarioSinActualizar.setTextoQuiero("Quiero");
        propietarioSinActualizar = propietarioRepository.save(propietarioSinActualizar);

        propietarioSinActualizar.setNombre("Lionel");
        propietarioSinActualizar.setApellido("Messi");
        propietarioSinActualizar.setDescripcion("Futbolista");
        propietarioSinActualizar.setUsername("liomessi");
        propietarioSinActualizar.setPassword("antonella");
        propietarioSinActualizar.setTextoSoy("Soy Leo");
        propietarioSinActualizar.setTextoHago("Hago magia");
        propietarioSinActualizar.setTextoQuiero("Quiero seguir jugando al futbol");
        propietarioService.actualizar(propietarioSinActualizar);

        Propietario propietarioGuardado = propietarioRepository.findById(propietarioSinActualizar.getId()).get();

        assertAll("Verifico que todos los datos se hayan actualizado",
                ()-> assertEquals(propietarioGuardado.getId(), propietarioGuardado.getId()),
                ()-> assertEquals("Lionel", propietarioGuardado.getNombre()),
                ()-> assertEquals("Messi", propietarioGuardado.getApellido()),
                ()-> assertEquals("Futbolista", propietarioGuardado.getDescripcion()),
                ()-> assertEquals("liomessi", propietarioGuardado.getUsername()),
                ()-> assertEquals("antonella", propietarioGuardado.getPassword()),
                ()-> assertEquals("Soy Leo", propietarioGuardado.getTextoSoy()),
                ()-> assertEquals("Hago magia", propietarioGuardado.getTextoHago()),
                ()-> assertEquals("Quiero seguir jugando al futbol", propietarioGuardado.getTextoQuiero())
                );
    }

    @Test
    public void actualizar_conCamposObligatoriosEnBlanco_lanzaExcepcion(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre(" ");
        propietarioGuardado.setApellido(" ");
        propietarioGuardado.setDescripcion(" ");
        propietarioGuardado.setUsername(" ");
        propietarioGuardado.setPassword(" ");
        propietarioGuardado.setTextoSoy(" ");
        propietarioGuardado.setTextoQuiero(" ");
        propietarioGuardado.setTextoHago(" ");

        assertThrows(IllegalArgumentException.class, ()->{
            propietarioService.actualizar(propietarioGuardado);
        });
    }

    @Test
    public void actualizar_conCamposObligatoriosNulos_lanzaExcepcion(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre(null);
        propietarioGuardado.setApellido(null);
        propietarioGuardado.setDescripcion(null);
        propietarioGuardado.setUsername(null);
        propietarioGuardado.setPassword(null);
        propietarioGuardado.setTextoSoy(null);
        propietarioGuardado.setTextoQuiero(null);
        propietarioGuardado.setTextoHago(null);

        assertThrows(IllegalArgumentException.class, ()->{
            propietarioService.actualizar(propietarioGuardado);
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

    @Test
    public void ver_conCualquierUsuario_retornaPropietario(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre("Lionel");
        propietarioGuardado.setApellido("Messi");
        propietarioGuardado.setDescripcion("Futbolista");
        propietarioGuardado.setUsername("leomessi");
        propietarioGuardado.setPassword("leoyanto4ever");
        propietarioRepository.save(propietarioGuardado);

        Propietario propietarioRetornado = propietarioService.ver();

        assertEquals("Messi", propietarioRetornado.getApellido());
    }

}
