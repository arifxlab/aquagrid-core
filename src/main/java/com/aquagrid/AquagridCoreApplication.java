package com.aquagrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AquagridCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquagridCoreApplication.class, args);
    }

}