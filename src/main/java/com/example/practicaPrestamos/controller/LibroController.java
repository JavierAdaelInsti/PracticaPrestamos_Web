package com.example.practicaPrestamos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practicaPrestamos.dto.LibroRequestDTO;
import com.example.practicaPrestamos.model.Libro;
import com.example.practicaPrestamos.service.LibroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@Valid @RequestBody LibroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.crearLibro(dto));
    }
}
