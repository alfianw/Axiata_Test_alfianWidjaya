/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.user;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.exception.ResourceNotFoundException;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.User;
import com.axiata.myboostTestMuhamadAlfianWidjaya.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alfia
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseApi<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseApi<>("00", "Success", users);
    }

    @Override
    public ResponseApi<User> getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return new ResponseApi<>("00", "Success", user);
    }

    @Override
    public ResponseApi<User> createUser(User user) {
        User savedUser = userRepository.save(user);
        return new ResponseApi<>("00", "User created successfully", savedUser);
    }

    @Override
    public ResponseApi<User> updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setUpdatedBy(user.getUpdatedBy());

        User updatedUser = userRepository.save(existingUser);
        return new ResponseApi<>("00", "User updated successfully", updatedUser);
    }

    @Override
    public ResponseApi<Void> deleteUser(Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(existingUser);
        return new ResponseApi<>("00", "User deleted successfully", null);
    }
}
