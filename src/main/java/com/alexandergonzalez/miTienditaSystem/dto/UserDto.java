package com.alexandergonzalez.miTienditaSystem.dto;

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

    public UserDto(String id, String name, String lastname, String username, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.createdAt = createdAt;
    }
}
