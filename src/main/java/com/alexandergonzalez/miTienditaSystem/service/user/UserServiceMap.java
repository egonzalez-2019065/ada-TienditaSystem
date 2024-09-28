package com.alexandergonzalez.miTienditaSystem.service.user;

import com.alexandergonzalez.miTienditaSystem.models.user.User;
import com.alexandergonzalez.miTienditaSystem.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMap {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceMap(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Declaración del método que guardará en la base de datos un usuario nuevo
    public User saveUser(User user){
        return userRepository.save(user);
    }

    // Declaración de un método que actualizará un usuario existente en la base de datos
    public User updateUser(String id, User user){
        if(userRepository.existsById(id)){
            return userRepository.save(user);
        }
        return null;
    }

    // Declaración de un método que devolverá la información de un solo usuario existente en la base de datos
    public Optional <User> findUserById(String id){
            return userRepository.findById(id);
    }

    // Declaración de un método que devolverá todos los usuarios existentes en la base de datos
    public List<User> getALlUsers(){
        return userRepository.findAll();
    }

    // Declaración de un método que eliminará un usuario existente en la base de datos
    public void deleteUser(String id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

}
