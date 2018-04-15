package agh.edu.pl.MapNotes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapNotesApplication.class)
@WebAppConfiguration
public class PinControllerTest extends BaseControllerTest {

    @Test
    public void getPins() throws Exception {
        mockMvc.perform(get("/pin"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(this.pins.size())));
    }

    @Test
    public void getSinglePin() throws Exception {
        mockMvc.perform(get("/pin/{id}", this.pin1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.pin1.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.pin1.getData())));
    }

    @Test
    public void deletePin() throws Exception {
        mockMvc.perform(delete("/pin/{id}", this.pin1.getId()))
                .andExpect(status().isOk());
    }

}
