package com.benimhoff.portfolioWeb.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VistasUsuarioControllerTest {

    @Autowired
    private VistasUsuarioController vistasUsuarioController;

    @Mock
    private Model model;

    @Test
    public void home_conCualquierUsuario_retornaHome(){
        String vistaHome = vistasUsuarioController.home(model);

        assertEquals("home", vistaHome);
    }
}
