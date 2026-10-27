package com.example.practicaPrestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practicaPrestamos.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
