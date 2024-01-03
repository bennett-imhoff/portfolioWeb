package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.exception.LoginExcepcion;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;
    @Autowired
    private PropietarioRepository propietarioRepository;
    @Mock
    private PropietarioService propietarioServiceMock;
    @InjectMocks
    private LoginController loginControllerMock;
    @Mock
    private Model model;

    @BeforeEach
    public void crearPropietarioPruebas(){
        Propietario propietario = new Propietario();
        propietario.setNombre("Nombre");
        propietario.setApellido("Apellido");
        propietario.setDescripcion("Descripcion");
        propietario.setUsername("Username");
        propietario.setPassword("Password");
        propietarioRepository.save(propietario);
    }

    @Test
    public void login_conCualquierUsuario_retornaVistaLogin(){
        String vistaLogin = loginController.login();

        assertNotNull(vistaLogin);
    }

    @Test
    public void ingresar_conUsuarioValido_retornaHomeEdicion(){
        String username = "Username";
        String password = "Password";

        String vistaLogin = loginController.ingresar(username, password);

        assertEquals("redirect:/homeEdicion", vistaLogin);
    }

    @Test
    public void ingresar_conUsuarioInvalido_lanzaExcepcion(){
        String username = "benn";
        String password = "benn";

        assertThrows(LoginExcepcion.class, ()->{
            loginController.ingresar(username, password);
        });
    }

    @Test
    public void homeEdicion_conUsuarioValido_retornaHomeEdicion(){
        loginController.ingresar("Username", "Password");

        String vistaHomeEdicion = loginController.homeEdicion(model);

        assertEquals("homeEdicion", vistaHomeEdicion);
    }

    @Test
    public void homeEdicion_conUsuarioNoLogueado_retornaLogin() throws NoSuchFieldException, IllegalAccessException{
        Field usuarioAutenticadoField = LoginController.class.getDeclaredField("usuarioAutenticado");
        usuarioAutenticadoField.setAccessible(true);
        usuarioAutenticadoField.set(loginControllerMock, false);

        String vistaHomeEdicion = loginControllerMock.homeEdicion(model);

        assertEquals("redirect:/login", vistaHomeEdicion);
    }
}
