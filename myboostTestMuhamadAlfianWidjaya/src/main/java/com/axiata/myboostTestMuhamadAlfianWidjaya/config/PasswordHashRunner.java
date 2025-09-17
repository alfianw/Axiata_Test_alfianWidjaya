/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.config;

import com.axiata.myboostTestMuhamadAlfianWidjaya.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hp
 */
@Component
@RequiredArgsConstructor
public class PasswordHashRunner implements CommandLineRunner{
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findAll().forEach(u -> {
            String pw = u.getPassword();
            if (pw != null && !pw.startsWith("$2a$") && !pw.startsWith("$2b$") && !pw.startsWith("$2y$")) {
                String encoded = passwordEncoder.encode(pw);
                u.setPassword(encoded);
                userRepository.save(u);
                System.out.println("Updated password for: " + u.getEmail());
            } else {
                System.out.println("Already encoded: " + u.getEmail());
            }
        });
    }
}
