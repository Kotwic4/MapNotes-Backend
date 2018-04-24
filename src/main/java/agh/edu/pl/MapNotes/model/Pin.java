package agh.edu.pl.MapNotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Pin note on map.
 * This class represents information about notes that are parts of {@link Map map}.
 * Each pin can only be in one map. Moreover each pin have his unique id.
 * All pin information(like geo-localization) are stored in json format in data field.
 * Backend don't make any assumptions about data stored in data field, so all validation should be on frontend side.
 *
 * Map property is ignored in json format to don't make dependency loop.
 *
 * @see agh.edu.pl.MapNotes.controller.PinController
 * @see Map
 * @see PinRepository
 */
@Entity
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Map map;

    private HashMap<String, Object> data;

    public Pin(HashMap<String, Object> data, Map map) {
        this.data = data;
        this.map = map;
    }

    Pin() {
        //jpa
    }

    public Long getId() {
        return id;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
