package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.Propietario;
import com.benimhoff.portfolioWeb.repository.PropietarioRepository;
import com.benimhoff.portfolioWeb.service.PropietarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VistasUsuarioControllerTest {

    @Autowired
    private VistasUsuarioController vistasUsuarioController;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Autowired
    private PropietarioService propietarioService;

    @Mock
    private Model model;

    @Test
    public void home_conCualquierUsuario_retornaHome(){
        Propietario propietarioGuardado = new Propietario();
        propietarioGuardado.setNombre("Lionel");
        propietarioGuardado.setApellido("Messi");
        propietarioGuardado.setDescripcion("Futbolista");
        propietarioGuardado.setUsername("leomessi");
        propietarioGuardado.setPassword("leoyanto4ever");
        propietarioRepository.save(propietarioGuardado);

        String vistaHome = vistasUsuarioController.home(model);

        verify(model).addAttribute(eq("propietario"), any(Propietario.class));
        assertEquals("home", vistaHome);
    }
}
