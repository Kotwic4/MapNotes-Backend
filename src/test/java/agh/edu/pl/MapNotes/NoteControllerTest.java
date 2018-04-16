package agh.edu.pl.MapNotes;

import agh.edu.pl.MapNotes.model.Note;
import agh.edu.pl.MapNotes.model.Pin;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NoteControllerTest extends BaseControllerTest {

    @Test
    public void getNoteFirstCaseTest() throws Exception {
        mockMvc.perform(get("/note/{id}", this.note.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.note.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.note.getData())));
//                .andExpect(jsonPath("$.data", hasSize(this.note.getPins().size())));
    }

    @Test
    public void getNoteSecondCaseTest() throws Exception {
        mockMvc.perform(get("/note/{id}", this.note2.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.note2.getId().intValue())))
                .andExpect(jsonPath("$.data", is(this.note2.getData())));
//                .andExpect(jsonPath("$.data", hasSize(this.note.getPins().size())));
    }

    @Test
    public void getAllNotesTest() throws Exception {
        mockMvc.perform(get("/note"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
        //        .andExpect(jsonPath("$.data", is(this.note.getData())));
        //  .andExpect(jsonPath("$.data", hasSize(this.note.getPins().size())));
    }


    @Test
    public void putNoteTest() throws Exception {
        Note note1 = createNote();
        mockMvc.perform(put("/note")
                .content(toJson(note1))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void putPinTest() throws Exception {
        Pin pin1 = createPin(note2);
        mockMvc.perform(put("/note/{noteid}/pin", note2.getId())
                .content(toJson(pin1))
                .contentType(contentType))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteNoteTest() throws Exception {
        mockMvc.perform(delete("/note/{id}", this.note.getId()))
                .andExpect(status().isOk());
    }



    private Note createNote() {
        HashMap<String, Object> noteData = new HashMap<String, Object>();
        noteData.put("name", "flats in Warsaw");
        Note newNote = new Note(noteData);
        return newNote;
    }

    private Pin createPin(Note note) {
        HashMap<String, Object> pinData1 =  new HashMap<>();
        pinData1.put("name", "2-bedroom flat");
        pinData1.put("size", "60");
        return new Pin(pinData1, note);
    }
}
