package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.exception.PinNotFoundException;
import agh.edu.pl.MapNotes.model.Pin;
import agh.edu.pl.MapNotes.model.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to manage {@link Pin pins}.
 * It only allow to get or delete pins.
 * To add or modify a pin go to {@link MapController map controller}.
 * @see Pin
 */
@RestController
@RequestMapping("/pin")
public class PinController {
    private final PinRepository pinRepository;

    @Autowired
    PinController(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    /**
     * Get all pins in the database.
     * Function should be used only to debug.
     * @return list of all pins in the database.
     */
    @GetMapping
    public List<Pin> getAllPin() {
        return pinRepository.findAll();
    }

    /**
     * Get information about specify pin.
     * @param pinId id of pin to get.
     * @return founded pin.
     * @throws PinNotFoundException when pin was not found in database.
     */
    @GetMapping("/{pinId}")
    public Pin getPin(@PathVariable("pinId") Long pinId) throws PinNotFoundException {
        return pinRepository.findById(pinId).orElseThrow(PinNotFoundException::new);
    }

    /**
     * Delete pin from database.
     * @param pinId id of pin to delete.
     */
    @DeleteMapping("/{pinId}")
    public void deletePin(@PathVariable("pinId") Long pinId) {
        pinRepository.deleteById(pinId);
    }
}
