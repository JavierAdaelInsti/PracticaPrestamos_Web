package com.example.practicaPrestamos.exception;

// Se lanza cuando no se encuentra un lector, libro o prestamo (404)
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
