package com.example.practicaPrestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practicaPrestamos.model.Lector;

public interface LectorRepository extends JpaRepository<Lector, Long> {
}
