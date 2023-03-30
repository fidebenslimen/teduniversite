package com.example.teduniversite.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@ToString
@Getter
@Setter
public class utilisateur_bloqué {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime lastFailedLoginAttempt;

    private LocalDateTime expiryTime;


@OneToOne
    private utilisateur user;
}
