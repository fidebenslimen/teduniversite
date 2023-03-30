package com.example.teduniversite.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString
@Getter
@Setter
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
