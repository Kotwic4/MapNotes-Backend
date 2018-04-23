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

/**
 * Controller to manage {@link Map maps}.
 * @see Map
 * @see PinController
 */
@RestController
@RequestMapping("/map")
public class MapController {
    private final MapRepository mapRepository;
    private final PinRepository pinRepository;

    @Autowired
    MapController(MapRepository mapRepository, PinRepository pinRepository) {
        this.mapRepository = mapRepository;
        this.pinRepository = pinRepository;
    }

    /**
     * Get all maps in the database.
     * @return list of all maps in the database.
     */
    @GetMapping
    public List<Map> getAllMaps() {
        return mapRepository.findAll();
    }

    /**
     * Update or create map.
     * Function allow to create map or to update.
     * If the id match id of any map it will update that map.
     * Otherwise it will create new map with new id.
     * Function allow to add multiple pins, but don't allow to delete them.
     * Update only fields that are passed in request body.
     * @param map map to update or create.
     * @return map stored in database after operation.
     */
    @PutMapping
    public Map putMap(@Valid @RequestBody Map map) {
        map = mapRepository.save(map);
        if(map.getPins() != null){
            for (Pin pin : map.getPins()) {
                pin.setMap(map);
                pinRepository.save(pin);
            }
        }
        return this.mapRepository.findById(map.getId()).get();
    }

    /**
     * Get information about specified map.
     * @param mapId id of map to get.
     * @return founded map.
     * @throws MapNotFoundException when map was not found in database.
     */
    @GetMapping("/{mapId}")
    public Map getMapById(@PathVariable("mapId") Long mapId) throws MapNotFoundException {
        return mapRepository.findById(mapId).orElseThrow(MapNotFoundException::new);
    }

    /**
     * Function allow to add or update one pin on specified map.
     * If the pinId match id of any pin it will update that pin.
     * Otherwise it will create new pin with new id.
     * @param mapId id of map to manipulate on.
     * @param pin pin to add or update.
     * @return pin stored in database after operation.
     * @throws MapNotFoundException when map was not found in database.
     */
    @PutMapping("/{mapId}/pin")
    public Pin addPin(@PathVariable("mapId") Long mapId, @Valid @RequestBody Pin pin) throws MapNotFoundException {
        Map map = mapRepository.findById(mapId).orElseThrow(MapNotFoundException::new);
        pin.setMap(map);
        return pinRepository.save(pin);
    }

    /**
     * Delete map from database.
     * All pins that are connected to this map will be deleted as well.
     * @param mapId id of map to delete.
     */
    @DeleteMapping("/{mapId}")
    public void deleteMapById(@PathVariable("mapId") Long mapId) {
        mapRepository.deleteById(mapId);
    }
}
