package com.spring.reminder.services;

import com.spring.reminder.models.User;
import com.spring.reminder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) { userRepository.save(user); }

    public List<User> findAll() { return userRepository.findAll(); }


    public User getUser(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User [userId="+id+"] can't be found"));
    }


    public ResponseEntity<User> updateUser(int id, User user) {
        return userRepository.findById(id).map(User ->{
            User.setFirstName(user.getFirstName());
            User.setLastName(user.getLastName());
            User.setEmail(user.getEmail());
            User.setPassword(user.getPassword());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User [userId="+id+"] can't be found"));
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
