package edu.MapNotes.note;

import edu.MapNotes.pin.Pin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "note")
    private List<Pin> pins;

    @NotNull
    private String data;

    public Note(@NotNull String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
