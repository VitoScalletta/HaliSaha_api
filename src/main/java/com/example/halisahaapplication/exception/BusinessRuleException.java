package com.example.halisahaapplication.exception;

public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(String mesaj){
        super(mesaj);
    }
}
