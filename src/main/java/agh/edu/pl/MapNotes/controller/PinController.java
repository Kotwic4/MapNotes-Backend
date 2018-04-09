package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.exception.PinNotFoundException;
import agh.edu.pl.MapNotes.model.Pin;
import agh.edu.pl.MapNotes.model.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pin")
public class PinController {
    private final PinRepository pinRepository;

    @Autowired
    public PinController(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    @GetMapping
    public List<Pin> getAllPin() {
        return pinRepository.findAll();
    }

    @GetMapping("/{pinId}")
    public Pin getPin(@PathVariable("pinId") Long pinId) throws PinNotFoundException {
        return pinRepository.findById(pinId).orElseThrow(PinNotFoundException::new);
    }

    @DeleteMapping("/{pinId}")
    public void deletePin(@PathVariable("pinId") Long pinId) {
        pinRepository.deleteById(pinId);
    }
}
