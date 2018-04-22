package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.model.Map;
import agh.edu.pl.MapNotes.model.Pin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.NestedServletException;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MapControllerTest extends BaseControllerTest {

    @Test
    void getMapFirstCaseTest() throws Exception {
        mockMvc.perform(get("/map/{id}", this.map1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.map1.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.map1.getData())))
                .andExpect(jsonPath("$.pins", hasSize(3)));
    }

    @Test
    void getMapSecondCaseTest() throws Exception {
        mockMvc.perform(get("/map/{id}", this.map2.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.map2.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.map2.getData())))
                .andExpect(jsonPath("$.pins", hasSize(0)));
    }

    @Test
    void getMapNotFoundTest() throws Exception {
        mockMvc.perform(get("/map/{id}", 100))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllMapsTest() throws Exception {
        mockMvc.perform(get("/map"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void putNewMapTest() throws Exception {
        Map map = createMap();
        mockMvc.perform(put("/map/")
                .content(toJson(map))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMapTest() throws Exception {
        mockMvc.perform(delete("/map/{id}", this.map1.getId()))
                .andExpect(status().isOk());
        assertEquals(1, this.mapRepository.findAll().size());
    }

    @Test
    void deleteMapNotFoundTest() throws Exception {
        assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(delete("/map/{id}", 100));});
        assertEquals(2, this.mapRepository.findAll().size());
    }


    private Map createMap() {
        HashMap<String, Object> noteData = new HashMap<String, Object>();
        noteData.put("name", "flats in Warsaw");
        return new Map(noteData);
    }

    private Pin createPin(Map map) {
        HashMap<String, Object> pinData1 =  new HashMap<>();
        pinData1.put("name", "2-bedroom flat");
        pinData1.put("size", "60");
        return new Pin(pinData1, map);
    }
}
