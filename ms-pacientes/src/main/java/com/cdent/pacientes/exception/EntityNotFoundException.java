package com.cdent.pacientes.exception;

/**
 * Excepción de dominio cuando no se encuentra un recurso solicitado.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String mensaje) {
        super(mensaje);
    }
}
