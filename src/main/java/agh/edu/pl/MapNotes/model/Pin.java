package agh.edu.pl.MapNotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Entity
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Note note;

    @NotNull
    private HashMap<String, Object> data;

    public Pin(@NotNull HashMap<String, Object> data, Note note) {
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

    public void setNote(Note note) {
        this.note = note;
    }
}
