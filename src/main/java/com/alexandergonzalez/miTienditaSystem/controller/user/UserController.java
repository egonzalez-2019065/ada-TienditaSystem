package com.alexandergonzalez.miTienditaSystem.controller.user;

import com.alexandergonzalez.miTienditaSystem.entity.User;
import com.alexandergonzalez.miTienditaSystem.dto.UserDto;
import com.alexandergonzalez.miTienditaSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user/")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint para poder crear un nuevo usuario
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.saveUser(userDto);
        return ResponseEntity.ok(user);
    }

    // Endpoint que devolverá todos los usuarios exisntentes en la base de datos
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getALlUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint que devuelve un usuario por su id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        UserDto userFound = userService.findUserById(id);
        if(userFound != null){
            return ResponseEntity.ok(userFound);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint que actualiza un usuario por su id
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        UserDto userFound = userService.findUserById(id);
        if(userFound != null){
            UserDto updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint que elimina un usuario existente por su id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
      UserDto userFound = userService.findUserById(id);
        if(userFound != null){
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
