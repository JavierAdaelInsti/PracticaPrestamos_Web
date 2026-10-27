package com.example.practicaPrestamos.exception;

// Se lanza cuando no se cumple una regla de negocio (409)
public class ReglaNegocioException extends RuntimeException {

    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
