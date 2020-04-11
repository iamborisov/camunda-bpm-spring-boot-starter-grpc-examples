package com.example.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication(scanBasePackages = {"org.camunda.bpm.engine.grpc.client", "com.fasterxml.jackson", "com.example.client"})
public class ExampleClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleClientApplication.class, args);
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

