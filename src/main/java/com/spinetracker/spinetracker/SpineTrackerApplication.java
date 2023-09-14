package com.spinetracker.spinetracker;

import com.spinetracker.spinetracker.global.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpineTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpineTrackerApplication.class, args);
    }

}
