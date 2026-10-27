package com.example.practicaPrestamos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectorRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private boolean activo = true;

    private boolean membresiaPremium;
}
