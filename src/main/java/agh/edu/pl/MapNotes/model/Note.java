package agh.edu.pl.MapNotes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "note")
    private List<Pin> pins;

    @NotNull
    private HashMap<String, Object> data;

    public Note(HashMap<String, Object> data) {
        this.data = data;
        this.pins = new LinkedList<>();
    }

    private Note() {
        //jpa
    }

    public Long getId() {
        return id;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public @NotNull HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
