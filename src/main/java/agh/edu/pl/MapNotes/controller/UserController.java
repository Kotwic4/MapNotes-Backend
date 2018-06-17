package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.exception.UserNotFoundException;
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
     * Update or create map.
     * Function allow to create user or to update.
     * If the id match id of any user it will update that user.
     * Otherwise it will create new user with new id.
     * @param user to update or create.
     * @return user stored in database after operation.
     */
    @PutMapping
    public User putUser(@Valid @RequestBody User user) {
        user = userRepository.save(user);
        return this.userRepository.findById(user.getId()).get();
    }

    /**
     * Get information about specified map.
     * @param userId id of map to get.
     * @return founded user.
     * @throws UserNotFoundException when user was not found in database.
     */
    @GetMapping("/{userId}")
    public User getMapById(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }


    /**
     * Delete map from database.
     * All pins that are connected to this map will be deleted as well.
     * @param userId id of map to delete.
     */
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId) {
        userRepository.deleteById(userId);
    }
}
