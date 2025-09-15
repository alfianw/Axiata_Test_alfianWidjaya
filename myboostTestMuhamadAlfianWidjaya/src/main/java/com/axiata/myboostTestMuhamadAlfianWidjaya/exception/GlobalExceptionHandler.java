/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.axiata.myboostTestMuhamadAlfianWidjaya.exception;

import com.axiata.myboostTestMuhamadAlfianWidjaya.dto.ResponseApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author alfia
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseApi<?>> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(404).body(new ResponseApi<>("40", e.getMessage(), null));
    }

    @ExceptionHandler(MandatoryFieldException.class)
    public ResponseEntity<ResponseApi<?>> handleFieldException(MandatoryFieldException e) {
        return ResponseEntity.badRequest().body(new ResponseApi<>("20", e.getMessage(), null));
    }

    @ExceptionHandler(FormatException.class)
    public ResponseEntity<ResponseApi<?>> handleFormat(FormatException e) {
        return ResponseEntity.badRequest().body(new ResponseApi<>("21", e.getMessage(), null));
    }

    @ExceptionHandler(FailedException.class)
    public ResponseEntity<ResponseApi<?>> handleFailed(FailedException e) {
        return ResponseEntity.badRequest().body(new ResponseApi<>("50", e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi<?>> handleAll(Exception e) {
        return ResponseEntity.internalServerError().body(new ResponseApi<>("90", "Service error: " + e.getMessage(), null));
    }
}
