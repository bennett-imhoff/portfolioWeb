package com.benimhoff.portfolioWeb.exception;

import org.springframework.core.NestedRuntimeException;

public class LoginExcepcion extends NestedRuntimeException {

    public LoginExcepcion(String mensaje){
        super(mensaje);
    }
}
