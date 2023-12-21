package com.benimhoff.portfolioWeb.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class LoginControllerTest {

    @Autowired
    LoginController loginController;

    @Mock
    private Model model;

    @Test
    public void ingresar_conCualquierUsuario_retornaVistaLogin(){
        String vistaLogin = loginController.verLogin(model);

        assertNotNull(vistaLogin);
    }
}
