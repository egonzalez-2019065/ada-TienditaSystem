package com.example.bookingSystem.service.user;

import com.example.bookingSystem.models.User;
import java.util.List;

public interface UserService {

    // Guardar a un usuario
    User saveUser(User user);

    // Actualizar a un usuario
    User updateUser(int id, User user);

    // Devolver a los usuarios creados
    List<User> getAllUsuarios();

    // Eliminar a un usuario
    User deleteUser(int id);


}
