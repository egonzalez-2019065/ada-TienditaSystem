package com.alexandergonzalez.miTienditaSystem.service;

import com.alexandergonzalez.miTienditaSystem.dto.UserDto;
import com.alexandergonzalez.miTienditaSystem.entity.User;
import com.alexandergonzalez.miTienditaSystem.repository.UserRepository;
import com.alexandergonzalez.miTienditaSystem.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private UserDto toDto(User user){
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getLastname(),
            user.getUsername(),
            user.getCreatedAt()
        );
    }

    // Declaración del método que guardará en la base de datos un usuario nuevo
    public UserDto saveUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return this.toDto(user);
    }

    // Declaración de un método que actualizará un usuario existente en la base de datos
    public UserDto updateUser(String id, UserDto userDto){
        User userFound = userRepository.findById(id).orElse(null);
        if(userFound != null){
            userFound.setName(userDto.getName());
            userFound.setLastname(userDto.getLastname());
            userFound.setUsername(userDto.getUsername());
            userRepository.save(userFound);
            return this.toDto(userFound);
        }
        return null;
    }

    // Declaración de un método que devolverá la información de un solo usuario existente en la base de datos
    public UserDto findUserById(String id){
           User userFound = userRepository.findById(id).orElse(null);
           if(userFound != null){
               return this.toDto(userFound);
           }
           return null;
    }

    // Declaración de un método que devolverá todos los usuarios existentes en la base de datos
    public List<UserDto> getALlUsers(){
        return userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    // Declaración de un método que eliminará un usuario existente en la base de datos
    public UserDto deleteUser(String id){
        User userFound = userRepository.findById(id).orElse(null);
        if(userFound != null){
            UserDto userDtoDeleted = toDto(userFound);
            userRepository.deleteById(id);
            return userDtoDeleted;
        }
        return null;
    }

}
