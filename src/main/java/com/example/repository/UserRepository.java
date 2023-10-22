

package com.example.repository;

import com.example.entity.User;
import com.example.entity.UserSession;

import java.util.List;
import java.util.Optional;

public interface UserRepository  {
    User save(User user);

    Optional<User> findById(Long id);

    User findByUsername(String username);
    List<User> findAll();

    void update(User user);

    void deleteById(Long id);

    public List<User> saveAll(List<User> users);

    User findByUsernameOrEmail(String usernameOrEmail);

    void addUserSessions(UserSession sessionInfo, User user);

    User findBySessionToken(String sessionToken);

    User findByEmail(String email);

//    UserSession getSessionByToken(String tokenValue);

//    void delete(User existingUser);
}

