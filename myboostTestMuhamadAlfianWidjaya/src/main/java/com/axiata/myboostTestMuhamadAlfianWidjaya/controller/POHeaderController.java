/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.controller;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.RequestPOHeader;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponsePOHeader;
import com.axiata.myboostTestMuhamadAlfianWidjaya.service.POHeader.POHeaderService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/poHeader")
@RequiredArgsConstructor
public class POHeaderController {

    private final POHeaderService poHeaderService;

    // GET all PO Header or one PO Header by id
    @GetMapping
    public ResponseApi<List<ResponsePOHeader>> getAllPO(@RequestParam(required = false) Integer id) {
        if (id != null) {
            ResponsePOHeader po = poHeaderService.getPOById(id).getData();
            return new ResponseApi<>("00", "Success", List.of(po));
        }
        return poHeaderService.getAllPO();
    }

    // CREATE PO Header
    @PostMapping
    public ResponseApi<ResponsePOHeader> createPO(@RequestBody RequestPOHeader request, Principal principal) {
        String currentUserEmail = principal.getName();
        return poHeaderService.createPO(request, currentUserEmail);
    }

    // UPDATE PO Header
    @PutMapping("/{id}")
    public ResponseApi<ResponsePOHeader> updatePO(@PathVariable Integer id, @RequestBody RequestPOHeader request, Principal principal) {
        String currentUserEmail = principal.getName();
        return poHeaderService.updatePO(id, request,currentUserEmail);
    }

    // DELETE PO Header
    @DeleteMapping("/{id}")
    public ResponseApi<Void> deletePO(@PathVariable Integer id) {
        return poHeaderService.deletePO(id);
    }

    @DeleteMapping("/detail/{id}")
    public ResponseEntity<ResponseApi<Void>> deletePODetail(@PathVariable("id") Integer detailId) {
        ResponseApi<Void> response = poHeaderService.deletePODetail(detailId);
        return ResponseEntity.ok(response);
    }
}
