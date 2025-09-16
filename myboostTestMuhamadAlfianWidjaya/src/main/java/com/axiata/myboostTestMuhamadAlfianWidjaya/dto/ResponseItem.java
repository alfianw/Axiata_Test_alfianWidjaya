/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hp
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseItem {

    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Integer cost;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;
}
