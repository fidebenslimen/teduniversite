package com.example.admission_service.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
@Data
@ToString
public class Image {
    @Id
    @GeneratedValue
    Long id;

    String name;

    String location;

    @Lob
    byte[] content;






    public Image(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Image(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public Image() {
    }


}
