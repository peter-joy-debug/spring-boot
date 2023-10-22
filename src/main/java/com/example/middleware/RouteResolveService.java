package com.example.middleware;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RouteResolveService {
    private final UserRepository userRepository;

    public RouteResolveService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getTokenUser(String email)
    {
        return this.userRepository.findByEmail(email);
    }
}