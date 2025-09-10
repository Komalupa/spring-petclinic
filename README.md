# PetClinic Assignment (Standalone)

This repository contains a small Spring Boot application implementing the assignment:
- Add pet attributes (temperament, length, weight, color) for pets.
- Endpoints:
  - `POST /api/pets/{petId}/attributes` - add or update attributes for a pet.
  - `GET  /api/pets/{petId}/attributes` - retrieve attributes for a pet.

Run locally:
```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

Example curl:
```bash
# Upsert attributes for pet 1
curl -X POST http://localhost:8080/api/pets/1/attributes \
  -H "Content-Type: application/json" \
  -d '{"temperament":"Playful","length":20.5,"weight":6.2,"color":"Brown"}'

# Get attributes
curl http://localhost:8080/api/pets/1/attributes
```

You can push this project to your GitHub fork/own repo and share the link with the assignment reviewer.
