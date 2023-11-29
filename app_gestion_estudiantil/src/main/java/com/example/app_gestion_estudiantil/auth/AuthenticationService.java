package com.example.app_gestion_estudiantil.auth;

import com.example.app_gestion_estudiantil.config.JwtService;
import com.example.app_gestion_estudiantil.user.UserRepository;
import com.example.app_gestion_estudiantil.service.UserService;
import com.example.app_gestion_estudiantil.user.Role;
import com.example.app_gestion_estudiantil.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .id(request.getId())
                //.apodo(request.getApodo())
                .firstname(request.getFirstname())
                //.lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                //.carrera(request.getCarrera())
                .role(Role.USER)
                .build();
        userService.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<User> ud=userRepository.findByEmail(request.getEmail());

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();


        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
