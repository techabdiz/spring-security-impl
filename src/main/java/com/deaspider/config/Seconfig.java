package com.deaspider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class Seconfig {

    public SecurityFilterChain configure(HttpSecurity http) throws Exception{ 

        return http.build();
    }
}