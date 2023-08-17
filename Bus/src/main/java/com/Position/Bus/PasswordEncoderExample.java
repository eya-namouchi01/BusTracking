package com.Position.Bus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
    public static void main(String[] args) {
        String password = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println("Mot de passe en clair : " + password);
        System.out.println("Mot de passe encodé : " + encodedPassword);
    }
}