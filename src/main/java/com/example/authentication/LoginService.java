package com.example.authentication;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email); //Issue Here
        User userToReturn = null;
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Password matches, user is authenticated2
            userToReturn = user;
            return userToReturn;
        }
        return userToReturn; // Handle login failure
    }
}
