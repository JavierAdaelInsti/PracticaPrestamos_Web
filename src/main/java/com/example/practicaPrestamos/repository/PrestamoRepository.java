package com.example.practicaPrestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practicaPrestamos.model.EstadoPrestamo;
import com.example.practicaPrestamos.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    // Cuenta los prestamos de un lector que tienen un estado determinado
    long countByLectorIdAndEstado(Long lectorId, EstadoPrestamo estado);
}
