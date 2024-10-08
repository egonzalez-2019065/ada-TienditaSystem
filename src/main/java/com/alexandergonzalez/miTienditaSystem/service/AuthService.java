package com.alexandergonzalez.miTienditaSystem.service;

import com.alexandergonzalez.miTienditaSystem.config.JwtService;
import com.alexandergonzalez.miTienditaSystem.dto.auth.AuthDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.LoginDto;
import com.alexandergonzalez.miTienditaSystem.dto.auth.RegisterDto;
import com.alexandergonzalez.miTienditaSystem.entity.User;
import com.alexandergonzalez.miTienditaSystem.repository.AuthRepository;
import com.alexandergonzalez.miTienditaSystem.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthDto login(final LoginDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = authRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthDto(token);
    }

    public RegisterDto register(final RegisterDto request){
        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        this.authRepository.save(user);
        // Mapear los datos del usuario a un UserDto y devolverlo
        RegisterDto userDto = new RegisterDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastname(user.getLastname());
        userDto.setUsername(user.getUsername());
        userDto.setCreatedAt(user.getCreatedAt());

        return userDto;
    }

}
