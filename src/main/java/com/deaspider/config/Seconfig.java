package com.deaspider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.deaspider.services.UserDetailService;

@EnableWebSecurity
@Configuration
public class Seconfig {


    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception { 
         http.authorizeHttpRequests((req)->{
            req.requestMatchers("/home/admin")
                .hasRole("ADMIN")
                .requestMatchers("/home/user")
                .hasRole("USER")
                .requestMatchers("/home/clerk")
                .hasRole("CLERK")
                .requestMatchers("/home")
                .permitAll()
                .anyRequest().authenticated();
        });

        http.formLogin();
        return http.build();
    }
   
    @Autowired
    public void configure(AuthenticationManagerBuilder builder,
    UserDetailService service) throws Exception { 
        /**
         * builder.inMemoryAuthentication()
            .withUser("tech.abdiz")
                .password("{noop}12345")
                    .roles("ADMIN", "CLERK");*/
        builder.userDetailsService(service);
    }
}
