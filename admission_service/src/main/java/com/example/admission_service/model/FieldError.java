package com.example.admission_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldError {
    private String field;
    private String errorCode;
}
