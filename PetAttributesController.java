package org.example.petclinic.api;

import java.util.NoSuchElementException;

import org.example.petclinic.model.PetAttributes;
import org.example.petclinic.service.PetAttributesService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets/{petId}/attributes")
public class PetAttributesController {

    private final PetAttributesService service;

    public PetAttributesController(PetAttributesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PetAttributesDto> get(@PathVariable Integer petId) {
        return service.getByPetId(petId)
            .map(PetAttributesMapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PetAttributesDto> upsert(
        @PathVariable Integer petId,
        @Validated @RequestBody PetAttributesDto body) {
        PetAttributes saved = service.upsert(petId, PetAttributesMapper.toEntity(body));
        return ResponseEntity.ok(PetAttributesMapper.toDto(saved));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Integer petId) {
        try {
            service.delete(petId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
