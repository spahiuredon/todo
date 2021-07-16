package ch.bbw.rs.todo.controller;

import ch.bbw.rs.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserController userManager;


    //login by comparing db with user input
    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody User user) {
        for (User u:userManager.getUsers()) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}