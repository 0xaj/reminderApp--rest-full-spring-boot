package com.spring.reminder.controllers;

import com.spring.reminder.models.User;
import com.spring.reminder.services.UserService;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController  {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id,
                                           @RequestBody User user) {
        return  userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") int id) {
        userService.deleteById(id);
    }

}
