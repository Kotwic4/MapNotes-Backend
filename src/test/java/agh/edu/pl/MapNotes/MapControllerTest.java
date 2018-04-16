package agh.edu.pl.MapNotes;

import agh.edu.pl.MapNotes.model.Map;
import agh.edu.pl.MapNotes.model.Pin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapNotesApplication.class)
@WebAppConfiguration
public class MapControllerTest extends BaseControllerTest {

    @Test
    public void getNoteFirstCaseTest() throws Exception {
        mockMvc.perform(get("/map/{id}", this.map1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.map1.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.map1.getData())))
                .andExpect(jsonPath("$.pins", hasSize(0)));
    }

    @Test
    public void getNoteSecondCaseTest() throws Exception {
        mockMvc.perform(get("/map/{id}", this.map2.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.map2.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.map2.getData())))
                .andExpect(jsonPath("$.pins", hasSize(0)));
    }

    @Test
    public void getAllNotesTest() throws Exception {
        mockMvc.perform(get("/map"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void putNoteTest() throws Exception {
        Map map = createMap();
        mockMvc.perform(put("/map/")
                .content(toJson(map))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void putPinTest() throws Exception {
        Pin pin1 = createPin(map2);
        mockMvc.perform(put("/map/{mapId}/pin", map2.getId())
                .content(toJson(pin1))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNoteTest() throws Exception {
        mockMvc.perform(delete("/map/{id}", this.map1.getId()))
                .andExpect(status().isOk());
    }

    private Map createMap() {
        HashMap<String, Object> noteData = new HashMap<String, Object>();
        noteData.put("name", "flats in Warsaw");
        Map newMap = new Map(noteData);
        return newMap;
    }

    private Pin createPin(Map map) {
        HashMap<String, Object> pinData1 =  new HashMap<>();
        pinData1.put("name", "2-bedroom flat");
        pinData1.put("size", "60");
        return new Pin(pinData1, map);
    }
}
