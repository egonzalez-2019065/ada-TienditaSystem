package com.example.bookingSystem;

import com.alexandergonzalez.miTienditaSystem.controller.AuthController;
import com.alexandergonzalez.miTienditaSystem.dto.UserDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.AuthDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.LoginDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.PasswordDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.RegisterDto;
import com.alexandergonzalez.miTienditaSystem.service.AuthService;
import com.alexandergonzalez.miTienditaSystem.util.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;


    private void setupSecurityContext(String username){
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(authentication.getName()).thenReturn(username);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void loginSuccess(){

        LoginDto user = new LoginDto( "usernametest", "passwordtest");
        AuthDto expectedAuth = new AuthDto("123444");

        when(authService.login(user)).thenReturn(expectedAuth);

        ResponseEntity<AuthDto> response = authController.login(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAuth, response.getBody());
    }

    @Test
    void registerSuccess() {
        RegisterDto registerRequest = new RegisterDto("User", "Test", "usertest", "password");        RegisterDto register = new RegisterDto("User", "Test", "usertest", "password");
        RegisterDto userRegistered = new RegisterDto("User", "Test", "usertest", "hashedPassword", Role.ADMIN, LocalDateTime.now());

        when(authService.register(registerRequest)).thenReturn(userRegistered);

        ResponseEntity<RegisterDto> response = authController.register(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userRegistered, response.getBody());
    }

    @Test
    void updatePasswordSuccess(){
        // Simular autenticación
        setupSecurityContext("userupdate");

        PasswordDto passwordDto = new PasswordDto("oldpasswordtest", "newpasswordtest");
        UserDto userDto = new UserDto("User", "Test", "userupdate", "oldpasswordtest");
        String id = "1233";
        when(authService.findUserById(id)).thenReturn(userDto);
        when(authService.updatePasword(id, passwordDto)).thenReturn(true);

        ResponseEntity<Object> response = authController.updatePassword(id, passwordDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        String passwordUpdated = responseBody.get("message");

        assertNotNull(passwordUpdated);
        assertEquals("Contraseña actualizada correctamente", passwordUpdated);
        verify(authService).updatePasword(id, passwordDto);

        SecurityContextHolder.clearContext();
    }

    @Test
    void updatePasswordError(){
        // Simular autenticación
       setupSecurityContext("userupdate");

        PasswordDto passwordDto = new PasswordDto("oldpasswordtest", "newpasswordtest");
        UserDto userDto = new UserDto("User", "Test", "userupdate", "password123");
        String id = "1233";
        when(authService.findUserById(id)).thenReturn(userDto);
        when(authService.updatePasword(id, passwordDto)).thenReturn(false);

        ResponseEntity<Object> response = authController.updatePassword(id, passwordDto);
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());

        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        String passwordUpdated = responseBody.get("message");

        assertNotNull(passwordUpdated);
        assertEquals("Las contraseñas no coinciden", passwordUpdated);
        verify(authService).updatePasword(id, passwordDto);

        SecurityContextHolder.clearContext();
    }

    @Test
    void updatePasswordUnauthorized(){
        // Simular autenticación
       setupSecurityContext("admin");

        PasswordDto passwordDto = new PasswordDto("oldpasswordtest", "newpasswordtest");
        UserDto userDto = new UserDto("User", "Test", "userupdate", "oldpasswordtest");
        String id = "1233";
        when(authService.findUserById(id)).thenReturn(userDto);

        ResponseEntity<Object> response = authController.updatePassword(id, passwordDto);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        String passwordUpdated = responseBody.get("message");

        assertNotNull(passwordUpdated);
        assertEquals("NO puedes actualizar otro usuario que no sea el tuyo", passwordUpdated);
        verify(authService, never()).updatePasword(any(), any());

        SecurityContextHolder.clearContext();

    }

}
