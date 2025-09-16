/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.user;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestLogin;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseLogin;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseUser;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.User;
import java.util.List;

/**
 *
 * @author alfia
 */
public interface UserService {

    ResponseApi<List<ResponseUser>> getAllUsers();

    ResponseApi<ResponseUser> getUserById(Integer id);

    ResponseApi<ResponseUser> createUser(User user);

    ResponseApi<ResponseUser> updateUser(Integer id, User user, String currentUserEmail);

    ResponseApi<ResponseUser> deleteUser(Integer id);
    
    ResponseApi<ResponseLogin> login(RequestLogin request);
}
