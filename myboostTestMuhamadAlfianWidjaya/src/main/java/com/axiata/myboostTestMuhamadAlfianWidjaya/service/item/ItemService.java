/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.item;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestItem;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseItem;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface ItemService {

    ResponseApi<List<ResponseItem>> getAllItems();

    ResponseApi<ResponseItem> getItemById(Integer id);

    ResponseApi<List<ResponseItem>> getItemByName(String name);

    ResponseApi<ResponseItem> createItem(RequestItem request, String currentUserEmail);

    ResponseApi<ResponseItem> updateItem(Integer id, RequestItem request, String currentUserEmail);

    ResponseApi<Void> deleteItem(Integer id);
}
