/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.user;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.User;
import java.util.List;

/**
 *
 * @author alfia
 */
public interface UserService {

    ResponseApi<List<User>> getAllUsers();

    ResponseApi<User> getUserById(Integer id);

    ResponseApi<User> createUser(User user);

    ResponseApi<User> updateUser(Integer id, User user);

    ResponseApi<Void> deleteUser(Integer id);
}
