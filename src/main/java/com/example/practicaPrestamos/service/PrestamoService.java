package com.example.practicaPrestamos.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.practicaPrestamos.dto.PrestamoRequestDTO;
import com.example.practicaPrestamos.exception.RecursoNoEncontradoException;
import com.example.practicaPrestamos.exception.ReglaNegocioException;
import com.example.practicaPrestamos.model.EstadoPrestamo;
import com.example.practicaPrestamos.model.Lector;
import com.example.practicaPrestamos.model.Libro;
import com.example.practicaPrestamos.model.Prestamo;
import com.example.practicaPrestamos.repository.LectorRepository;
import com.example.practicaPrestamos.repository.LibroRepository;
import com.example.practicaPrestamos.repository.PrestamoRepository;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LectorRepository lectorRepository;
    private final LibroRepository libroRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, LectorRepository lectorRepository,
            LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.lectorRepository = lectorRepository;
        this.libroRepository = libroRepository;
    }

    @Transactional
    public Prestamo solicitarPrestamo(PrestamoRequestDTO dto) {
        // 1. Validar que el lector exista y este activo
        Lector lector = lectorRepository.findById(dto.getLectorId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "El lector con id " + dto.getLectorId() + " no existe"));
        if (!lector.isActivo()) {
            throw new ReglaNegocioException("El lector esta inactivo o bloqueado");
        }

        // 2. Validar que el libro exista y tenga ejemplares disponibles
        Libro libro = libroRepository.findById(dto.getLibroId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "El libro con id " + dto.getLibroId() + " no existe"));
        if (libro.getEjemplaresDisponibles() < 1) {
            throw new ReglaNegocioException("El libro no tiene ejemplares disponibles");
        }

        // 3. Validar el limite de prestamos activos (PRESTADO o VENCIDO)
        long prestados = prestamoRepository.countByLectorIdAndEstado(lector.getId(), EstadoPrestamo.PRESTADO);
        long vencidos = prestamoRepository.countByLectorIdAndEstado(lector.getId(), EstadoPrestamo.VENCIDO);
        long prestamosActivos = prestados + vencidos;

        int limite = 3;
        if (lector.isMembresiaPremium()) {
            limite = 5;
        }
        if (prestamosActivos >= limite) {
            throw new ReglaNegocioException("El lector ya alcanzo el limite de " + limite + " prestamos activos");
        }

        // 4. Restar 1 a los ejemplares disponibles
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
        libroRepository.save(libro);

        // 5. Crear el prestamo
        Prestamo prestamo = new Prestamo();
        prestamo.setLector(lector);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionMaxima(LocalDate.now().plusDays(14));
        prestamo.setEstado(EstadoPrestamo.PRESTADO);
        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo devolverLibro(Long prestamoId) {
        // 1. Validar que el prestamo exista y este PRESTADO o VENCIDO
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "El prestamo con id " + prestamoId + " no existe"));
        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new ReglaNegocioException("El prestamo ya fue devuelto");
        }

        // 2. Cambiar el estado a DEVUELTO
        prestamo.setEstado(EstadoPrestamo.DEVUELTO);

        // 3. Sumar 1 a los ejemplares disponibles del libro
        Libro libro = prestamo.getLibro();
        libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
        libroRepository.save(libro);

        // 4. Si se devolvio tarde, se bloquea al lector
        if (LocalDate.now().isAfter(prestamo.getFechaDevolucionMaxima())) {
            Lector lector = prestamo.getLector();
            lector.setActivo(false);
            lectorRepository.save(lector);
        }

        return prestamoRepository.save(prestamo);
    }
}
