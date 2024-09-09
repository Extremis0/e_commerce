package com.tanmay.ecommerce_backend.api.Security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public  SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{

       http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());

        http.csrf(csrf -> csrf.disable());
       return http.build();
    }


}

