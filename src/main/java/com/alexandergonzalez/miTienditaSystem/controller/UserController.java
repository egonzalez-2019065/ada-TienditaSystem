package com.alexandergonzalez.miTienditaSystem.controller;

import com.alexandergonzalez.miTienditaSystem.dto.RoleDto;
import com.alexandergonzalez.miTienditaSystem.dto.UserDto;
import com.alexandergonzalez.miTienditaSystem.entity.User;
import com.alexandergonzalez.miTienditaSystem.repository.AuthRepository;
import com.alexandergonzalez.miTienditaSystem.service.UserService;
import com.alexandergonzalez.miTienditaSystem.util.Role;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/user/")

public class UserController {

    private final UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        loadSampleUsers();
    }

    public void loadSampleUsers() {
        UserDto found = userService.findByUsername("admin");
        if(found == null) {
            UserDto userAdmin = new UserDto("admin", "admin", "admin", "admin");
            userService.saveUser(userAdmin);
        }
    }

    // Endpoint para poder crear un nuevo usuario
    @RolesAllowed("ADMIN")
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
    public ResponseEntity<Object> findById(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        UserDto userFound = userService.findUserById(id);
        if(userFound != null){
            response.put("Usuario encontrado", userFound);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("message", "El usuario que está buscando aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Endpoint que actualiza un usuario por su id
    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto userFound = userService.findUserById(id);
        if(userFound != null){
            if(currentPrincipalName.equals(userFound.getUsername())){
                UserDto updatedUser = userService.updateUser(id, userDto);
                response.put("Usuario actualizado", updatedUser);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.put("message","NO puedes actualizar otro usuario que no sea el tuyo");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
        response.put("message", "El usuario que está buscando aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/role/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable String id, @RequestBody RoleDto newRole) {
        Map<String, String> response = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto userFound = userService.findUserById(id);
        if(userFound != null){
            Boolean roleUpdated = userService.updateRole(id, newRole, currentPrincipalName);
            if(roleUpdated){
                response.put("message", "Role actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message","Role no actualizado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "El usuario que está buscando aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    // Endpoint que elimina un usuario existente por su id
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto userFound = userService.findUserById(id);
        if(userFound != null){
            if(currentPrincipalName.equals(userFound.getUsername())){
                UserDto userDeleted = userService.deleteUser(id);
                response.put("Usuario eliminado", userDeleted.getUsername());
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                response.put("message","NO puedes eliminar otro usuario que no sea el tuyo");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
        response.put("message", "El usuario que está buscando aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
