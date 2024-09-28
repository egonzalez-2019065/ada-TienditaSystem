package com.alexandergonzalez.miTienditaSystem.controller.user;

import com.alexandergonzalez.miTienditaSystem.models.user.User;
import com.alexandergonzalez.miTienditaSystem.models.user.UserDto;
import com.alexandergonzalez.miTienditaSystem.service.user.UserServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")

public class UserController {

    private final UserServiceMap userServiceMap;

    @Autowired
    public UserController(UserServiceMap userServiceMap) {
        this.userServiceMap = userServiceMap;
    }

    // Endpoint para poder crear un nuevo usuario
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        User userSaved = userServiceMap.saveUser(user);
        URI createdUserURI = URI.create("/v1/user/" + userSaved.getId());
        return ResponseEntity.created(createdUserURI).build();
    }

    // Endpoint que devolverá todos los usuarios exisntentes en la base de datos
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServiceMap.getALlUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint que devuelve un usuario por su id
    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        Optional<User> userFound = userServiceMap.findUserById(id);
        if(userFound.isPresent()){
            return ResponseEntity.ok(userFound.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint que actualiza un usuario por su id
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        Optional<User> userFound = userServiceMap.findUserById(id);
        if(userFound.isPresent()){
            User userToUpdate = userFound.get();
            userToUpdate.update(userDto);
            userServiceMap.saveUser(userToUpdate);
            return ResponseEntity.ok(userToUpdate);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint que elimina un usuario existente por su id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        Optional<User> userFound = userServiceMap.findUserById(id);
        if(userFound.isPresent()){
            userServiceMap.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
