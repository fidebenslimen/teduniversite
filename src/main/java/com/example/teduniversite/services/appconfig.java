package com.example.teduniversite.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appconfig {
   @Bean
public IFileLocationService fileLocationService() {
    return new FileLocationServices(); // Replace with your implementation
}
}
