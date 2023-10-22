package com.example.samples;

import com.example.entity.User;
import com.example.entity.UserStatus;
import com.example.entity.UserType;
import com.example.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SampleDataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Create sample users
        List<User> sampleUsers = new ArrayList<>();

        User user1 = new User("John", "Doe", "johndoe", "johndoe@example.com","123", "123456789", "123 Main St", "USA", UserType.CLIENT, UserStatus.ENABLED, new ArrayList<>(), new ArrayList<>());
        User user2 = new User("Alice", "Johnson", "alicej", "alicej@example.com","134", "987654321", "456 Elm St", "Canada", UserType.SUPPLIER, UserStatus.ENABLED, new ArrayList<>(),new ArrayList<>());
        // Add more sample users as needed
        User user3 = new User("Bob", "Smith", "bobsmith", "bobsmith@example.com","145", "555555555", "789 Oak St", "UK", UserType.CLIENT, UserStatus.ENABLED, new ArrayList<>(),new ArrayList<>());

        // Encoding Passwords
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
        user3.setPassword(passwordEncoder.encode(user3.getPassword()));

        sampleUsers.add(user1);
        sampleUsers.add(user2);
        sampleUsers.add(user3);

        // Save the sample users
        userRepository.saveAll(sampleUsers);
    }
}