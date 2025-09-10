package org.example.petclinic.repository;

import java.util.Optional;

import org.example.petclinic.model.PetAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetAttributesRepository extends JpaRepository<PetAttributes, Integer> {
    Optional<PetAttributes> findByPetId(Integer petId);
    void deleteByPetId(Integer petId);
    boolean existsByPetId(Integer petId);
}
