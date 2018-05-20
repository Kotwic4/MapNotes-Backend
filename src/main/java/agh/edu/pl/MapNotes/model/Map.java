package agh.edu.pl.MapNotes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Map is an entity, which groups pins.
 * Map is equivalent of excel sheet.
 * Its have its unique id.
 * Its also have lists of all {@link Pin pins} connected to this map.
 * Moreover each map can have additional information.
 * This information can be stored in json format in data property.
 * Backend don't make any assumptions about data stored in data field, so all validation should be on frontend side.
 *
 * @see agh.edu.pl.MapNotes.controller.MapController
 * @see Pin
 * @see MapRepository
 */
@Entity
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "map")
    private List<Pin> pins;

    @Lob
    private HashMap<String, Object> data;

    public Map(HashMap<String, Object> data) {
        this.data = data;
        this.pins = new LinkedList<>();
    }

    private Map() {
        //jpa
    }

    public Long getId() {
        return id;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
