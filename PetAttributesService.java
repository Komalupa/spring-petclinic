package org.example.petclinic.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.example.petclinic.model.Pet;
import org.example.petclinic.model.PetAttributes;
import org.example.petclinic.repository.PetAttributesRepository;
import org.example.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetAttributesService {

    private final PetRepository petRepository;
    private final PetAttributesRepository attributesRepository;

    public PetAttributesService(PetRepository petRepository,
                                PetAttributesRepository attributesRepository) {
        this.petRepository = petRepository;
        this.attributesRepository = attributesRepository;
    }

    @Transactional(readOnly = true)
    public Optional<PetAttributes> getByPetId(Integer petId) {
        return attributesRepository.findByPetId(petId);
    }

    @Transactional
    public PetAttributes upsert(Integer petId, PetAttributes attrs) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new NoSuchElementException("Pet not found: " + petId));

        PetAttributes target = attributesRepository.findByPetId(petId).orElse(new PetAttributes());
        target.setPet(pet);
        target.setTemperament(attrs.getTemperament());
        target.setLength(attrs.getLength());
        target.setWeight(attrs.getWeight());
        target.setColor(attrs.getColor());
        return attributesRepository.save(target);
    }

    @Transactional
    public void delete(Integer petId) {
        if (!petRepository.existsById(petId)) {
            throw new NoSuchElementException("Pet not found: " + petId);
        }
        attributesRepository.deleteByPetId(petId);
    }
}
