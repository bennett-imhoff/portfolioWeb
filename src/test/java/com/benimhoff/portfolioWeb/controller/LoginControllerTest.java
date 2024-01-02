package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.exception.LoginExcepcion;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LoginControllerTest {

    @Autowired
    LoginController loginController;

    @Autowired
    PropietarioRepository propietarioRepository;

    @Mock
    private Model model;

    @Test
    public void verLogin_conCualquierUsuario_retornaVistaLogin(){
        String vistaLogin = loginController.verLogin();

        assertNotNull(vistaLogin);
    }

    @Test
    public void ingresar_conUsuarioValido_retornaHomeEdicion(){
        Propietario propietario = new Propietario();
        propietario.setNombre("Nombre");
        propietario.setApellido("Apellido");
        propietario.setDescripcion("Descripcion");
        propietario.setUsername("Username");
        propietario.setPassword("Password");
        propietarioRepository.save(propietario);

        String username = "Username";
        String password = "Password";

        String vistaLogin = loginController.ingresar(username, password);

        assertEquals("redirect:/homeEdicion", vistaLogin);
    }

    @Test
    public void ingresar_conUsuarioInvalido_lanzaExcepcion(){
        Propietario propietario = new Propietario();
        propietario.setNombre("Nombre");
        propietario.setApellido("Apellido");
        propietario.setDescripcion("Descripcion");
        propietario.setUsername("Username");
        propietario.setPassword("Password");
        propietarioRepository.save(propietario);

        String username = "benn";
        String password = "benn";


        assertThrows(LoginExcepcion.class, ()->{
            loginController.ingresar(username, password);
        });
    }
}
