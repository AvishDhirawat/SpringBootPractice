package com.firstspring.project.firstprojectspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests( auth -> auth.anyRequest().authenticated() ); // To add Authentication for all the requests

        http.httpBasic(withDefaults()); // Shows Login webpage/alert if request is not Authenticated

        return http.build();
    }

}
