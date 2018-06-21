package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.model.User;
import agh.edu.pl.MapNotes.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users in the database.
     * @return list of all users in the database.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Create user.
     * Function allow to create user.
     * @param user to create.
     * @return user stored in database after operation.
     */
    @PutMapping
    public User putUser(@Valid @RequestBody User user) {
        user = userRepository.save(user);
        return this.userRepository.findById(user.getId()).get();
    }

}
