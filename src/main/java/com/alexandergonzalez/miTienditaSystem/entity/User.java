package com.alexandergonzalez.miTienditaSystem.entity;

import com.alexandergonzalez.miTienditaSystem.dto.UserDto;
import com.alexandergonzalez.miTienditaSystem.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Document(collection = "users")
public class User{
    @Id
    private String id;

    private String name;
    private String lastname;
    private String username;
    private String password;
    private Role role;
    @CreatedDate
    private LocalDateTime createdAt;

    public User() {
        this.createdAt = LocalDateTime.now();
    }
}
