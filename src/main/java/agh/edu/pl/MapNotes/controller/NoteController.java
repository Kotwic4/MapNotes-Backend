package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.exception.NoteNotFoundException;
import agh.edu.pl.MapNotes.model.Note;
import agh.edu.pl.MapNotes.model.NoteRepository;
import agh.edu.pl.MapNotes.model.Pin;
import agh.edu.pl.MapNotes.model.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PutMapping
    public Note putNote(@Valid @RequestBody Note note) {
        note = noteRepository.save(note);
        for(Pin pin: note.getPins()){
            pin.setNote(note);
            pinRepository.save(pin);
        }
        return note;
    }

    @GetMapping("/{noteId}")
    public Note getNoteById(@PathVariable("noteId") Long noteId) throws NoteNotFoundException {
        return noteRepository.findById(noteId).orElseThrow(NoteNotFoundException::new);
    }

    @PutMapping("/{noteId}/pin")
    public Pin addPin(@PathVariable("noteId") Long noteId,
                      @Valid @RequestBody Pin pin) throws NoteNotFoundException {
        Note note = noteRepository.findById(noteId).orElseThrow(NoteNotFoundException::new);
        pin.setNote(note);
        return pinRepository.save(pin);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNoteById(@PathVariable("noteId") Long noteId) {
        noteRepository.deleteById(noteId);
    }
}
