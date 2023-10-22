package com.example.configuration;

import com.example.repository.UserRepository;
import com.example.repository.UserRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryInMemory();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
