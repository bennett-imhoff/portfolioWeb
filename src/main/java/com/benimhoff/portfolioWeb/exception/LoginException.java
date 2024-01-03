package com.benimhoff.portfolioWeb.exception;

import org.springframework.core.NestedRuntimeException;

public class LoginException extends NestedRuntimeException {

    public LoginException(String mensaje){
        super(mensaje);
    }
}
