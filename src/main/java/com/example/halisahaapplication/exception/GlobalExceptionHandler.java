package com.example.halisahaapplication.exception;

import com.example.halisahaapplication.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFound(ResourceNotFoundException exception){
        ErrorResponse errorBox = new ErrorResponse(
                exception.getMessage(),
                    404,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorBox, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessRuleException(BusinessRuleException exception){
        ErrorResponse errorBox = new ErrorResponse(
                exception.getMessage(),
                400,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorBox,HttpStatus.BAD_REQUEST);
    }

}
