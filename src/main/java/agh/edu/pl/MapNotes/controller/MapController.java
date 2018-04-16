package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.exception.MapNotFoundException;
import agh.edu.pl.MapNotes.model.Map;
import agh.edu.pl.MapNotes.model.MapRepository;
import agh.edu.pl.MapNotes.model.Pin;
import agh.edu.pl.MapNotes.model.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {
    private final MapRepository mapRepository;
    private final PinRepository pinRepository;

    @Autowired
    public MapController(MapRepository mapRepository, PinRepository pinRepository) {
        this.mapRepository = mapRepository;
        this.pinRepository = pinRepository;
    }

    @GetMapping
    public List<Map> getAllMaps() {
        return mapRepository.findAll();
    }

    @PutMapping
    public Map putMap(@Valid @RequestBody Map map) {
        map = mapRepository.save(map);
        if(map.getPins() != null){
            for (Pin pin : map.getPins()) {
                pin.setMap(map);
                pinRepository.save(pin);
            }
        }
        return map;
    }

    @GetMapping("/{mapId}")
    public Map getMapById(@PathVariable("mapId") Long mapId) throws MapNotFoundException {
        return mapRepository.findById(mapId).orElseThrow(MapNotFoundException::new);
    }

    @PutMapping("/{mapId}/pin")
    public Pin addPin(@PathVariable("mapId") Long noteId, @Valid @RequestBody Pin pin) throws MapNotFoundException {
        Map map = mapRepository.findById(noteId).orElseThrow(MapNotFoundException::new);
        pin.setMap(map);
        return pinRepository.save(pin);
    }

    @DeleteMapping("/{mapId}")
    public void deleteMapById(@PathVariable("mapId") Long mapId) {
        mapRepository.deleteById(mapId);
    }
}
