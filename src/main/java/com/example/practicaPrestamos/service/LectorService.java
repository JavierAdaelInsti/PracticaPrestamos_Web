package com.example.practicaPrestamos.service;

import org.springframework.stereotype.Service;

import com.example.practicaPrestamos.dto.LectorRequestDTO;
import com.example.practicaPrestamos.model.Lector;
import com.example.practicaPrestamos.repository.LectorRepository;

@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public Lector crearLector(LectorRequestDTO dto) {
        Lector lector = new Lector();
        lector.setNombre(dto.getNombre());
        lector.setActivo(dto.isActivo());
        lector.setMembresiaPremium(dto.isMembresiaPremium());
        return lectorRepository.save(lector);
    }
}
