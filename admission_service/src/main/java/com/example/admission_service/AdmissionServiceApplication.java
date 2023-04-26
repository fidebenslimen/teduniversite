package com.example.admission_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class AdmissionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmissionServiceApplication.class, args);
    }

}
