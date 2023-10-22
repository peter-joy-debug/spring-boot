//package com.example.repository;
//import com.example.entity.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Repository
//public class UserRepositoryInMemory {
//
//    private final Map<Long, User> users = new ConcurrentHashMap<>();
//    private Long nextUserId = 1L;
//
//    public List<User> getAllUsers() {
//        return new ArrayList<>(users.values());
//    }
//
//    public Optional<User> getUserById(Long id) {
//        return Optional.ofNullable(users.get(id));
//    }
//
//    public User createUser(User user) {
//        user.setUserID(nextUserId++);
//        users.put(user.getUserID(), user);
//        return user;
//    }
//
//    public User updateUser(User user) {
//        if (users.containsKey(user.getUserID())) {
//            users.put(user.getUserID(), user);
//        }
//        return user;
//    }
//
//    public void deleteUser(Long id) {
//        users.remove(id);
//    }
//
//    // Add more custom query methods as needed
//}
package com.example.repository;

import com.example.entity.User;
import com.example.entity.UserSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepositoryInMemory implements UserRepository {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong nextUserId = new AtomicLong(1);

    @Override
    public User save(User user) {
        user.setUserID(nextUserId.getAndIncrement());
        users.put(user.getUserID(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User findByUsername(String username) {
        User user = users.get(username); // Assuming users is a Map or a similar data structure.
        if (user == null) {
            // User not found, you can handle this case as needed.
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }


    @Override
    public List<User> findAll() {

        return List.copyOf(users.values());
    }


    @Override
    public void update(User user) {
        if (users.containsKey(user.getUserID())) {
            users.put(user.getUserID(), user);
        }
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    @Override
    public List<User> saveAll(List<User> users) {
        for (User user : users) {
            save(user);
        }
        return users;
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        if (isValidEmail(usernameOrEmail)) {
            // Input is a valid email, proceed to look up by email.
            User user = findByEmail(usernameOrEmail);
            if (user != null) {
                return user;
            }
        } else if (isValidUsername(usernameOrEmail)) {
            // Input is a valid username, proceed to look up by username.
            User user = findByUsername(usernameOrEmail);
            if (user != null) {
                return user;
            }
        }

        // If we reach here, the user was not found by username or email.
        throw new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail);
    }

    @Override
    public void addUserSessions(UserSession sessionInfo, User user) {
        user.getUserSessions().add(sessionInfo);
    }

    @Override
    public User findBySessionToken(String sessionToken) {
        // You can iterate through all users and their sessions to find a match for the given session token
        for (User user : users.values()) { // Assuming you have a map of users
            if (user.getUserSessions() != null) {
                for (UserSession session : user.getUserSessions()) {
                    if (sessionToken.equals(session.getAuthToken())) {
                        return user;
                    }
                }
            }
        }
        return null;
    }


    private boolean isValidEmail(String input) {
        // Define a regular expression pattern for a valid email address.
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Check if the input matches the email pattern.
        return input.matches(emailPattern);
    }
    private boolean isValidUsername(String input) {
        // Implement your username validation logic here.

        // Example: A valid username should be at least 3 characters long and only contain letters, numbers, or underscores.
        String usernamePattern = "^[a-zA-Z0-9_]{3,}$";

        // Check if the input matches the username pattern.
        return input.matches(usernamePattern);
    }
    @Override
   public User findByEmail(String email) {
        // Assuming you have a ConcurrentHashMap where you can look up users by their email.
        // This allows for efficient and thread-safe retrieval.
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            if (entry.getKey().toString().equals(email)) {
                System.out.println("Found");
                return entry.getValue(); // Return the user with the matching email.
            }
        }
        System.out.println("Not Found");
        return null; // If no user is found with the provided email.
    }







}
