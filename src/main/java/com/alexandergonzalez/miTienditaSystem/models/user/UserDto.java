package com.alexandergonzalez.miTienditaSystem.models.user;

public class UserDto {
    private final String name;

    private final String email;
    private final String password;

    public UserDto() {
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public UserDto(String name, String lastName, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDto(String name, String lastName, String email) {
        this.name = name;
        this.email = email;
        this.password = "";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
