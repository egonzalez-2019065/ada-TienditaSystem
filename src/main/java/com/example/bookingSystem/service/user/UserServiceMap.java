package com.example.bookingSystem.service.user;

import com.example.bookingSystem.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMap implements UserService {
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User updateUser(int id, User user) {
        return null;
    }

    @Override
    public List<User> getAllUsuarios() {
        return List.of();
    }

    @Override
    public User deleteUser(int id) {
        return null;
    }
}
