package ch.bbw.rs.todo.controller;

import ch.bbw.rs.todo.entity.Task;
import ch.bbw.rs.todo.entity.User;
import ch.bbw.rs.todo.repository.ApplicationUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    //register a user
    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }


    //get all users in a list
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return applicationUserRepository.findAll();
    }


    //find one user by the id
    /*
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public User findUser(@PathVariable long id) {
        User existingUser = applicationUserRepository.findById(id).get();
        Assert.notNull(existingUser, "User not found");
        return existingUser;
    }
*/

    //change username of a user by id
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editUser(@RequestBody User user, @PathVariable long id) {
        User existingUser = applicationUserRepository.findById(id).get();
        Assert.notNull(existingUser, "User not found");
        existingUser.setUsername(user.getUsername());
        applicationUserRepository.save(existingUser);
    }


    //delete a user by id
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        User userToDel = applicationUserRepository.findById(id).get();
        applicationUserRepository.delete(userToDel);
    }
}