package com.example.practicaPrestamos.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lector lector;

    @ManyToOne
    private Libro libro;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionMaxima;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;
}
