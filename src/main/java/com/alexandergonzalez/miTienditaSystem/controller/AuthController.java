package com.alexandergonzalez.miTienditaSystem.controller;

import com.alexandergonzalez.miTienditaSystem.dto.UserDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.AuthDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.LoginDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.PasswordDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.RegisterDto;
import com.alexandergonzalez.miTienditaSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto login){
        AuthDto authDto = this.authService.login(login);
        return ResponseEntity.ok(authDto);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register(@RequestBody RegisterDto dto){
        RegisterDto userDto = this.authService.register(dto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePassword(@PathVariable String id, @RequestBody PasswordDto passwordDto) {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto userFound = this.authService.findUserById(id);
        if(userFound != null){
            if(currentPrincipalName.equals(userFound.getUsername())){
                Boolean updatedUser = this.authService.updatePasword(id, passwordDto);
                if(updatedUser){
                    response.put("message:", "Contraseña actualizada correctamente");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
                response.put("message:","Las contraseñas no coinciden");
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
            }
            response.put("message:","NO puedes actualizar otro usuario que no sea el tuyo");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        response.put("message", "El usuario que está buscando aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



}
