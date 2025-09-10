package org.example.petclinic.api;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class PetAttributesDto {
    @Size(max = 100)
    public String temperament;

    @PositiveOrZero
    public Double length;

    @PositiveOrZero
    public Double weight;

    @Size(max = 50)
    public String color;
}
