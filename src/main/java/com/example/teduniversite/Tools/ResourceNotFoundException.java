package com.example.teduniversite.Tools;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String achievement, String id, Long id1) {
    }
}
