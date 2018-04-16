package agh.edu.pl.MapNotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Map map;

    private HashMap<String, Object> data;

    public Pin(HashMap<String, Object> data, Map map) {
        this.data = data;
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
