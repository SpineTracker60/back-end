package com.spinetracker.spinetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpineTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpineTrackerApplication.class, args);
    }

}
