package agh.edu.pl.MapNotes.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PinControllerTest extends BaseControllerTest {

    @Test
    void getPins() throws Exception {
        mockMvc.perform(get("/pin"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getSinglePin() throws Exception {
        mockMvc.perform(get("/pin/{id}", this.pin1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.pin1.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.pin1.getData())));
    }

    @Test
    void deletePin() throws Exception {
        mockMvc.perform(delete("/pin/{id}", this.pin1.getId()))
                .andExpect(status().isOk());
        assertEquals(2,this.pinRepository.findAll().size());
    }
}
