package ch.bbw.rs.todo.controller;

import ch.bbw.rs.todo.entity.Task;
import ch.bbw.rs.todo.entity.User;
import ch.bbw.rs.todo.repository.ApplicationUserRepository;
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

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return applicationUserRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editUser(@RequestBody User user, @PathVariable long id) {
        User existingUser = applicationUserRepository.findById(id).get();
        Assert.notNull(existingUser, "User not found");
        existingUser.setUsername(user.getUsername());
        applicationUserRepository.save(existingUser);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        User userToDel = applicationUserRepository.findById(id).get();
        applicationUserRepository.delete(userToDel);
    }
}