package com.example.admission_service.util;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException( String message) {


        super(message);
    }
}
