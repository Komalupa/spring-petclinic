package org.example.petclinic.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.example.petclinic.model.PetAttributes;
import org.example.petclinic.service.PetAttributesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PetAttributesController.class)
class PetAttributesControllerTest {

    @Autowired MockMvc mvc;
    @MockBean PetAttributesService service;

    @Test
    void get_returns_404_when_missing() throws Exception {
        given(service.getByPetId(7)).willReturn(Optional.empty());
        mvc.perform(get("/api/pets/7/attributes"))
            .andExpect(status().isNotFound());
    }

    @Test
    void post_upserts_and_returns_payload() throws Exception {
        PetAttributes saved = new PetAttributes();
        saved.setColor("Brown");
        saved.setTemperament("Calm");
        saved.setLength(20.0);
        saved.setWeight(4.5);

        given(service.upsert(eq(5), any(PetAttributes.class))).willReturn(saved);

        mvc.perform(post("/api/pets/5/attributes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"temperament":"Calm","length":20.0,"weight":4.5,"color":"Brown"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.temperament").value("Calm"))
            .andExpect(jsonPath("$.color").value("Brown"));
    }
}
