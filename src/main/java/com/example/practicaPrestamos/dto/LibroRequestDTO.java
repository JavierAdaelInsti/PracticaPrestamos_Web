package com.example.practicaPrestamos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroRequestDTO {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @Min(value = 0, message = "Los ejemplares no pueden ser negativos")
    private int ejemplaresDisponibles;
}
