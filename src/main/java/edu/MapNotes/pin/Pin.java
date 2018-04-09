package edu.MapNotes.pin;

import edu.MapNotes.note.Note;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Note note;

    @NotNull
    private String data;

    public Pin(@NotNull String data, Note note) {
        this.data = data;
    }

    Pin() {
        //jpa
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
