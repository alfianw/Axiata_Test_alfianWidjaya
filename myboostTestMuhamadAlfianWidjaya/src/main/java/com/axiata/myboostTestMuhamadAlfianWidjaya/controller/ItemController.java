/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.controller;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestItem;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseItem;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.service.item.ItemService;
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
 * @author Hp
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // GET all items or one item by id
    @GetMapping
    public ResponseApi<List<ResponseItem>> getAllItems(@RequestParam(required = false) Integer id) {
        if (id != null) {
            ResponseItem item = itemService.getItemById(id).getData();
            return new ResponseApi<>("00", "Success", List.of(item));
        }
        return itemService.getAllItems();
    }

    //Find by name
    @PostMapping("/search")
    public ResponseApi<List<ResponseItem>> getItemByName(@RequestBody RequestItem request) {
        String name = request.getName();
        return itemService.getItemByName(name);
    }

    // CREATE item
    @PostMapping
    public ResponseApi<ResponseItem> createItem(@RequestBody ResponseItem itemDto) {
        return itemService.createItem(itemDto);
    }

    // UPDATE item
    @PutMapping("/{id}")
    public ResponseApi<ResponseItem> updateItem(@PathVariable Integer id, @RequestBody ResponseItem itemDto) {
        return itemService.updateItem(id, itemDto);
    }

    // DELETE item
    @DeleteMapping("/{id}")
    public ResponseApi<Void> deleteItem(@PathVariable Integer id) {
        return itemService.deleteItem(id);
    }
}
