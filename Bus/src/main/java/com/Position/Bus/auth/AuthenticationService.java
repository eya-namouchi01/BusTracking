 package com.Position.Bus.auth;

import com.Position.Bus.Model.Role;
import com.Position.Bus.Repository.UserRepository;
import com.Position.Bus.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.Position.Bus.Model.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

 @Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserCode(),
                        request.getPassword()
                )
        );
        var user = repository.findByUserCode(request.getUserCode())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }




        public  String generateCUID() {
            StringBuilder randomString = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                char letter = (char) (random.nextInt(26) + 'A');
                randomString.append(letter);
            }
            for (int i = 0; i < 4; i++) {
                int digit = random.nextInt(10);
                randomString.append(digit);
            }

            return randomString.toString();
        }



    public AuthenticationResponse register(UserRequest request) {
        var user = new User();

        user.setUserCode(generateCUID());
        user.setPassword(passwordEncoder.encode("Sofrecom123#"));
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setRole(Role.USER);
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
