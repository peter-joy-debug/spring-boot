//package com.example.service;
//
//import com.example.entity.User;
//import com.example.entity.UserStatus;
//import com.example.entity.UserType;
//import com.example.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
////    private final UserRepository userRepository;
////
////    @Autowired
////    public UserService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
////
////    public List<User> getAllUsers() {
////        return (List<User>) userRepository.findAll();
////    }
////
////    public Optional<User> getUserById(Long id) {
////        return userRepository.findById(id);
////    }
////
////    public User createUser(User user) {
////        return userRepository.save(user);
////    }
////
////    public User updateUser(User user) {
////        return userRepository.save(user);
////    }
////
////    public void deleteUser(Long id) {
////        userRepository.deleteById(id);
////    }
//
////    public List<User> getUsersByUserType(UserType userType) {
////        return userRepository.findByUserType(userType);
////    }
////
////    public List<User> getUsersByAccountStatus(UserStatus accountStatus) {
////        return userRepository.findByAccountStatus(accountStatus);
////    }
//
//    // Add more service methods for custom queries as needed
//}
package com.example.service;

import com.example.entity.User;
import com.example.exceptions.UserNotFoundException;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Encode the user's password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        // Update the user fields as needed
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setEmail(updatedUser.getEmail());
        // Update other fields as needed

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Remove the password field from each user
//        users.forEach(user -> user.setPassword(null));
        return users;
    }

//    public List<User> saveAll(List<User> users) {
//        return userRepository.saveAll(users);
//    }
    public List<User> saveAll(List<User> users) {
    for (User user : users) {
        // Encode the user's password before saving
        encodeUserPassword(user);
        System.out.println(user.getPassword());
    }
    return userRepository.saveAll(users);
    }
    private void encodeUserPassword(User user) {
        String rawPassword = user.getPassword();

        if (rawPassword != null && !rawPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encodedPassword);
        }
    }
    public User findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }
}
