/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Hp
 */
@Data
public class RequestPOHeader {

    private String description;
    private String createdBy;
    private String updatedBy;
    private Integer itemId;
    private Integer itemQty;
    private Integer itemCost;
    private Integer itemPrice;
}
