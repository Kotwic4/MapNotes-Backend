package agh.edu.pl.MapNotes.controller;

import agh.edu.pl.MapNotes.exception.UserNotFoundException;
import agh.edu.pl.MapNotes.model.User;
import agh.edu.pl.MapNotes.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
     * Update or create user.
     * Function allow to create user or to update.
     * If the id match id of any user it will update that user.
     * Otherwise it will create new user with new id.
     * @param user map to update or create.
     * @return map stored in database after operation.
     */
    @PutMapping
    public User putUser(@Valid @RequestBody User user) {
        user = userRepository.save(user);
        return this.userRepository.findById(user.getId()).get();
    }


    @RequestMapping("/addIfNotExist")
    public Boolean putUserIfNotInDatabase(@RequestBody User user) {
        if (this.isUserCredentialsRight(user)) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    /**
     * Get information about specified user.
     * @param userId id of user to get.
     * @return founded user.
     * @throws UserNotFoundException when user was not found in database.
     */
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @RequestMapping("/exists")
    public Boolean isUserCredentialsRight (@RequestBody User user) {
        List<User> users =  userRepository.findAll();
        HashMap<String, Object> userData  = user.getData();
        for (User currentUser : users ){
            HashMap<String, Object> data = currentUser.getData();
            if (data.equals(userData)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete user from database.
     * @param userId id of user to delete.
     */
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId) {
        userRepository.deleteById(userId);
    }
}
