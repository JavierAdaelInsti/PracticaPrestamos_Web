package com.example.practicaPrestamos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practicaPrestamos.dto.PrestamoRequestDTO;
import com.example.practicaPrestamos.model.Prestamo;
import com.example.practicaPrestamos.service.PrestamoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<Prestamo> solicitarPrestamo(@Valid @RequestBody PrestamoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.solicitarPrestamo(dto));
    }

    @PutMapping("/{id}/devolucion")
    public ResponseEntity<Prestamo> devolverLibro(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.devolverLibro(id));
    }
}
