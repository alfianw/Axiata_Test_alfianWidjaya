/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.service.item;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseItem;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.exception.ResourceNotFoundException;
import com.axiata.myboostTestMuhamadAlfianWidjaya.model.Item;
import com.axiata.myboostTestMuhamadAlfianWidjaya.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hp
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseApi<List<ResponseItem>> getAllItems() {
        List<ResponseItem> items = itemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, ResponseItem.class))
                .collect(Collectors.toList());
        return new ResponseApi<>("00", "Success", items);
    }

    @Override
    public ResponseApi<ResponseItem> getItemById(Integer id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        return new ResponseApi<>("00", "Success", modelMapper.map(item, ResponseItem.class));
    }

    @Override
    public ResponseApi<List<ResponseItem>> getItemByName(String name) {
        List<Item> items = itemRepository.findByNameContainingIgnoreCase(name);
        if (items.isEmpty()) {
            throw new ResourceNotFoundException("No items found with name " + name);
        }
        List<ResponseItem> responseItems = items.stream()
                .map(item -> modelMapper.map(item, ResponseItem.class))
                .toList();
        return new ResponseApi<>("00", "Success", responseItems);
    }

    @Override
    public ResponseApi<ResponseItem> createItem(ResponseItem itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        Item saved = itemRepository.save(item);
        return new ResponseApi<>("00", "Item created successfully", modelMapper.map(saved, ResponseItem.class));
    }

    @Override
    public ResponseApi<ResponseItem> updateItem(Integer id, ResponseItem itemDto) {
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));

        existing.setName(itemDto.getName());
        existing.setDescription(itemDto.getDescription());
        existing.setPrice(itemDto.getPrice());
        existing.setCost(itemDto.getCost());
        existing.setUpdatedBy(itemDto.getUpdatedBy());

        Item updated = itemRepository.save(existing);
        return new ResponseApi<>("00", "Item updated successfully", modelMapper.map(updated, ResponseItem.class));
    }

    @Override
    public ResponseApi<Void> deleteItem(Integer id) {
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        itemRepository.delete(existing);
        return new ResponseApi<>("00", "Item deleted successfully", null);
    }
}
