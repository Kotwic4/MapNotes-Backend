package edu.MapNotes.note;

import edu.MapNotes.pin.Pin;
import edu.MapNotes.pin.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    private final NoteRepository noteRepository;
    private final PinRepository pinRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository, PinRepository pinRepository) {
        this.noteRepository = noteRepository;
        this.pinRepository = pinRepository;
    }

    @GetMapping
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    @PutMapping
    public Note putNote(@RequestBody Note note){
        return noteRepository.save(note);
    }

    @GetMapping("/{noteId}")
    public Note getNoteById(@PathVariable("noteId") Long noteId) throws NoteNotFoundException {
        return noteRepository.findById(noteId).orElseThrow(NoteNotFoundException::new);
    }

    @PutMapping("/{noteId}")
    public Pin addPin(@PathVariable("noteId") Long noteId,
                      @RequestBody Pin pin) throws NoteNotFoundException {
        Note note = noteRepository.findById(noteId).orElseThrow(NoteNotFoundException::new);
        Pin pin2 = new Pin(pin.getData(),note);
        return pinRepository.save(pin2);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNoteById(@PathVariable("noteId") Long noteId){
        noteRepository.deleteById(noteId);
    }
}
