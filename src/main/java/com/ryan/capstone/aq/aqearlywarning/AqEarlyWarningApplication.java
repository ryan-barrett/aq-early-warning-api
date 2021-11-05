package com.ryan.capstone.aq.aqearlywarning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class AqEarlyWarningApplication {
    public static void main(String[] args) {
        SpringApplication.run(AqEarlyWarningApplication.class, args);
    }
}

@Configuration
@EnableScheduling
class SpringConfig {
}
