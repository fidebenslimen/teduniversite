package com.example.admission_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ErrorReponse {
    private Integer httpStatus;
    private String exception;
    private String message;
    private List<FieldError> fieldErrors;
}
