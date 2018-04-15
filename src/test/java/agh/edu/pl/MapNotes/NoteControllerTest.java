package agh.edu.pl.MapNotes;

import agh.edu.pl.MapNotes.model.Note;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NoteControllerTest extends BaseControllerTest {

    @Test
    public void getNote() throws Exception {
        mockMvc.perform(get("/note/{id}", this.note.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.note.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.note.getData())));
//                .andExpect(jsonPath("$.data", hasSize(this.note.getPins().size())));
    }

    @Test
    public void putNote() throws Exception {
        Note note1 = createNote();
        mockMvc.perform(put("/note")
                .content(toJson(note1))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNote() throws Exception {
        mockMvc.perform(delete("/note/{id}", this.note.getId()))
                .andExpect(status().isOk());
    }

    private Note createNote() {
        HashMap<String, Object> noteData = new HashMap<String, Object>();
        noteData.put("name", "flats in Warsaw");
        Note newNote = new Note(noteData);
        return newNote;

    }
}
