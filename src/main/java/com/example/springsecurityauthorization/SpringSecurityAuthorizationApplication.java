package com.example.springsecurityauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAuthorizationApplication.class, args);
    }

    @Bean
    public ArrayList<String> getNames() {
        return new ArrayList<>();
    }

}
