package org.example.petclinic.api;

import org.example.petclinic.model.PetAttributes;

public final class PetAttributesMapper {

    private PetAttributesMapper() {}

    public static PetAttributes toEntity(PetAttributesDto dto) {
        PetAttributes e = new PetAttributes();
        e.setTemperament(dto.temperament);
        e.setLength(dto.length);
        e.setWeight(dto.weight);
        e.setColor(dto.color);
        return e;
    }

    public static PetAttributesDto toDto(PetAttributes e) {
        PetAttributesDto dto = new PetAttributesDto();
        dto.temperament = e.getTemperament();
        dto.length = e.getLength();
        dto.weight = e.getWeight();
        dto.color = e.getColor();
        return dto;
    }
}
