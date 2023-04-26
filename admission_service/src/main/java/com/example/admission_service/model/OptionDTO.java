package com.example.admission_service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
@Getter
@Setter
public class OptionDTO {
    private Long idOption;

    @Size(max = 255)
    private String nomOption;

}
