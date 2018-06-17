package agh.edu.pl.MapNotes.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;


/**
 * User is an entity, which groups pins.
 * User is equivalent of excel sheet.
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

    private String mail;
    private String password;

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

