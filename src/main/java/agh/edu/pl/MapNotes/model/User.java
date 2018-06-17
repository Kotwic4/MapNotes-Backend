package agh.edu.pl.MapNotes.model;

import javax.persistence.*;
import java.util.HashMap;


/**
 * User is an entity, which contain user info.
 * Its have its unique id.
 * User has password and mail for authentication.
 *
 * @see User
 * @see UserRepository
 */


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private HashMap<String, Object> data;

    public User(HashMap<String, Object> data) {
        this.data = data;
    }

    private User() {
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

}

