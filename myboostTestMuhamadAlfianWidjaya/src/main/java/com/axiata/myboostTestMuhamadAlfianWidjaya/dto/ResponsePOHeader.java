/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Hp
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePOHeader {

    private Integer id;
    private LocalDateTime datetime;
    private String description;
    private Integer totalPrice;
    private Integer totalCost;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;
    private List<ResponsePODetail> details;
}
