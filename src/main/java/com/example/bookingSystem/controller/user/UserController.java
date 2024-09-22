package com.example.bookingSystem.controller.user;

import com.example.bookingSystem.models.user.User;
import com.example.bookingSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/v1/users/")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createUser() {
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<User> updateUser() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        return ResponseEntity.ok().build();
    }

}
