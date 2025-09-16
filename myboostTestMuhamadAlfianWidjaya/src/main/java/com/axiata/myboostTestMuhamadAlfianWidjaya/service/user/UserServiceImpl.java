/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.user;

import com.axiata.myboostTestMuhamadAlfianWidjaya.config.JwtUtil;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestLogin;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseLogin;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseUser;
import com.axiata.myboostTestMuhamadAlfianWidjaya.exception.ResourceNotFoundException;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.User;
import com.axiata.myboostTestMuhamadAlfianWidjaya.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author alfia
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private ResponseUser convertToResponseUser(User user) {
        return modelMapper.map(user, ResponseUser.class);
    }

    @Override
    public ResponseApi<List<ResponseUser>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseUser> responseUsers = users.stream()
                .map(this::convertToResponseUser)
                .collect(Collectors.toList());
        return new ResponseApi<>("00", "Success", responseUsers);
    }

    @Override
    public ResponseApi<ResponseUser> getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return new ResponseApi<>("00", "Success", convertToResponseUser(user));
    }

    @Override
    public ResponseApi<ResponseUser> createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered: " + user.getEmail());
        }
        user.setCreatedBy(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return new ResponseApi<>("00", "User created successfully", convertToResponseUser(savedUser));
    }

    @Override
    public ResponseApi<ResponseUser> updateUser(Integer id, User user, String currentUserEmail) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhone(user.getPhone());
        existingUser.setUpdatedBy(currentUserEmail);
        System.out.println("DEBUG principal.getName() = " + currentUserEmail);

        User updatedUser = userRepository.save(existingUser);
        return new ResponseApi<>("00", "User updated successfully", convertToResponseUser(updatedUser));
    }

    @Override
    public ResponseApi<ResponseUser> deleteUser(Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(existingUser);
        return new ResponseApi<>("00", "User deleted successfully", convertToResponseUser(existingUser));
    }

    @Override
    public ResponseApi<ResponseLogin> login(RequestLogin request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        ResponseLogin responseLogin = ResponseLogin.builder()
                .token(token)
                .type("Bearer")
                .expiresIn(jwtUtil.getJwtExpirationMs())
                .email(user.getEmail())
                .build();

        return new ResponseApi<>("00", "Login success", responseLogin);
    }
}
