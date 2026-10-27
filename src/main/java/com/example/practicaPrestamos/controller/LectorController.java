package com.example.practicaPrestamos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practicaPrestamos.dto.LectorRequestDTO;
import com.example.practicaPrestamos.model.Lector;
import com.example.practicaPrestamos.service.LectorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lectores")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @PostMapping
    public ResponseEntity<Lector> crearLector(@Valid @RequestBody LectorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lectorService.crearLector(dto));
    }
}
