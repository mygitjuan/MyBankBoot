package com.dxc.mypersonalbankapi.exceptions;

public class ClienteValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClienteValidationException(String message) {
        super(message);
    }
}
