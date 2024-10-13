package com.example.bookingSystem;

import com.alexandergonzalez.miTienditaSystem.controller.UserController;
import com.alexandergonzalez.miTienditaSystem.dto.RoleDto;
import com.alexandergonzalez.miTienditaSystem.dto.UserDto;
import com.alexandergonzalez.miTienditaSystem.entity.User;
import com.alexandergonzalez.miTienditaSystem.service.UserService;
import com.alexandergonzalez.miTienditaSystem.util.Role;
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
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private UserService userService;


    @InjectMocks
    private UserController userController;


    @Test
    void createUser(){
        UserDto userDto = new UserDto("User", "Test", "usertest", "usertest123");

        when(userService.saveUser(userDto)).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.createUser(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto.getUsername(), response.getBody().getUsername());
    }

    @Test
    void findUser(){
        UserDto userDto = new UserDto("User", "Test", "usertest", "usertest123");
        String id = "1230";
        when(userService.findUserById(id)).thenReturn(userDto);

        ResponseEntity<Object> respone = userController.findById(id);

        Map<String, UserDto> responseBody = (Map<String, UserDto>) respone.getBody();

        UserDto userFound = responseBody.get("Usuario encontrado");

        assertEquals(HttpStatus.OK, respone.getStatusCode());
        assertNotNull(userFound);
        assertEquals("User", userFound.getName());
        assertEquals("usertest", userFound.getUsername());
    }

    @Test
    void findUserError(){
        String id = "1230";
        when(userService.findUserById(id)).thenReturn(null);

        ResponseEntity<Object> response = userController.findById(id);

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

        Object userNotFound = responseBody.get("message");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(userNotFound);
        assertEquals("El usuario que está buscando aún no existe", userNotFound);
    }

    @Test
    void updateUser(){

        // Simular autenticación
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("userupdate");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserDto userDto = new UserDto("User", "Update", "userupdate", LocalDateTime.now());
        String id = "123";

        when(userService.findUserById(id)).thenReturn(userDto);
        when(userService.updateUser(id, userDto)).thenReturn(userDto);
        ResponseEntity<Object> response = userController.updateUser(id, userDto);

        Map<String, UserDto> responseBody = (Map<String, UserDto>) response.getBody();
        UserDto  userUpdated = responseBody.get("Usuario actualizado");


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(userUpdated);
        assertEquals("userupdate", userUpdated.getUsername());
    }

    @Test
    void changeRole(){
        // Simular autenticación
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("admin");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        UserDto userDto = new UserDto("User", "Update", "userupdate", LocalDateTime.now());
        RoleDto roleDto = new RoleDto(Role.ADMIN);
        String id = "123";
        when(userService.findUserById(id)).thenReturn(userDto);
        when(userService.updateRole(id, roleDto, authentication.getName())).thenReturn(true);

        ResponseEntity<Object> response = userController.updateRole(id, roleDto);
        Map<String, String> responseBody = (Map<String, String>) response.getBody();

        String roleChanged = responseBody.get("message");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
        assertEquals("Role actualizado correctamente", roleChanged);

    }

    @Test
    void deleteUser(){
        // Simular autenticación
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("userdeleted");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        UserDto userDto = new UserDto("User", "Update", "userdeleted", LocalDateTime.now());
        String id = "123";

        when(userService.findUserById(id)).thenReturn(userDto);
        when(userService.deleteUser(id)).thenReturn(userDto);

        ResponseEntity<Object> response = userController.deleteUser(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object userDeleted = responseBody.get("Usuario eliminado");

        assertNotNull(responseBody);
        assertEquals("userdeleted", userDeleted);

    }

}
