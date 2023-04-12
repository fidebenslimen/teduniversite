package com.example.admission_service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.example.admission_service.domain")
@EnableJpaRepositories("com.example.admission_service.repos")
@EnableTransactionManagement
public class DomainConfig {

}
