package com.example.practicaPrestamos.service;

import org.springframework.stereotype.Service;

import com.example.practicaPrestamos.dto.LibroRequestDTO;
import com.example.practicaPrestamos.model.Libro;
import com.example.practicaPrestamos.repository.LibroRepository;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro crearLibro(LibroRequestDTO dto) {
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setEjemplaresDisponibles(dto.getEjemplaresDisponibles());
        return libroRepository.save(libro);
    }
}
