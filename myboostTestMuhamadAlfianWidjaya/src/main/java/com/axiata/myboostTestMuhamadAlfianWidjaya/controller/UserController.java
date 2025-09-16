/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.controller;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestLogin;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseLogin;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseUser;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.User;
import com.axiata.myboostTestMuhamadAlfianWidjaya.service.user.UserService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET all users or one user by id
    @GetMapping
    public ResponseApi<List<ResponseUser>> getAllUsers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            ResponseUser user = userService.getUserById(id).getData();
            return new ResponseApi<>("00", "Success", List.of(user));
        }
        return userService.getAllUsers();
    }

    // POST create user
    @PostMapping("/addUser")
    public ResponseApi<ResponseUser> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // PUT update user by id
    @PutMapping("/{id}")
    public ResponseApi<ResponseUser> updateUser(@PathVariable Integer id, @RequestBody User user, Authentication authentication) {
        String currentUserEmail = authentication.getName(); 
        return userService.updateUser(id, user, currentUserEmail);
    }

    // DELETE user by id
    @DeleteMapping("/{id}")
    public ResponseApi<ResponseUser> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    //POST login
    @PostMapping("/login")
    public ResponseApi<ResponseLogin> login(@RequestBody RequestLogin request) {
        return userService.login(request);
    }
}
