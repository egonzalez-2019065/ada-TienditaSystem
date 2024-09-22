package com.example.bookingSystem.service.user;

import com.example.bookingSystem.models.user.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    // Guardar a un usuario
    User saveUser(User user);

    // Actualizar a un usuario
    User updateUser(int id, User user);

    // Devolver a los usuarios creados
    List<User> getAllUsuarios();

    // Buscar a un usuario por su id
    Optional<User>  getUser(int id);

    // Eliminar a un usuario
    void deleteUser(int id);


}
