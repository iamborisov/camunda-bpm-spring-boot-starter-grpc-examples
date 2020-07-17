package com.example.server;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"com.example.server", "org.camunda.bpm.engine.grpc.server.**"})
@EnableTransactionManagement
@EnableProcessApplication("grpc-example")
public class ExampleServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleServerApplication.class, args);
    }
}
