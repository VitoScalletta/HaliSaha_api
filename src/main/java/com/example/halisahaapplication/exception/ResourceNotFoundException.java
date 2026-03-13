package com.example.halisahaapplication.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mesaj){
        super(mesaj);
    }
}
