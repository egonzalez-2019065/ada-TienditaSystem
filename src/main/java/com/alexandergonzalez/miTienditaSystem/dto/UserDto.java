package com.alexandergonzalez.miTienditaSystem.dto;

import com.alexandergonzalez.miTienditaSystem.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String id;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public UserDto(String id, String name, String lastname, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDto(String name, String lastname, String username, String password) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public UserDto(String name, String lastname, String username, LocalDateTime updatedAt) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.updatedAt = updatedAt;
    }
}
