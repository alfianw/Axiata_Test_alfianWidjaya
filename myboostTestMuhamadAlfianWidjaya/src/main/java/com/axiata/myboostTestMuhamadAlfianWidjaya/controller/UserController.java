/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.controller;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.User;
import com.axiata.myboostTestMuhamadAlfianWidjaya.service.user.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alfia
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET all users or one user by id
    @GetMapping
    public ResponseApi<List<User>> getAllUsers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            User user = userService.getUserById(id).getData();
            return new ResponseApi<>("00", "Success", List.of(user));
        }
        return userService.getAllUsers();
    }

    // POST create user
    @PostMapping
    public ResponseApi<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // PUT update user by id
    @PutMapping("/{id}")
    public ResponseApi<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // DELETE user by id
    @DeleteMapping("/{id}")
    public ResponseApi<User> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
