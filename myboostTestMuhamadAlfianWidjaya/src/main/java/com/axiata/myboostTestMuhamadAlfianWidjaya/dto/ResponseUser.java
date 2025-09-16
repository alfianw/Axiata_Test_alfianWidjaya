/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author alfia
 */
@Data
public class ResponseUser {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;
}
