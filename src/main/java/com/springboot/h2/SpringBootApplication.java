package com.springboot.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//lombok annotation for logger
@Slf4j
//spring annotation
@SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
        log.info("Springboot and dynamodb application started successfully.");
    }
}
