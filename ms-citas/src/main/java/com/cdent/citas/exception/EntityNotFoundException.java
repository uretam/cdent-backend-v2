package com.cdent.citas.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String mensaje) {
        super(mensaje);
    }
}
