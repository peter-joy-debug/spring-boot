//package com.example.authentication;
//
//import com.example.entity.User;
//import com.example.entity.UserSession;
//import com.example.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Service
//public class AuthService {
//    private final UserRepository userRepository;
//    private final AtomicLong sessionCounter = new AtomicLong(1);
//
//    public AuthService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public UserSession generateSession(User user) {
//        String tokenValue = UUID.randomUUID().toString();
//        LocalDateTime creationTime = LocalDateTime.now();
//        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30); // Token expires in 30 minutes
//
//        UserSession session = new UserSession(tokenValue, creationTime, expirationTime, "ACTIVE");
//
//        userRepository.addUserSessions(session,user);
//        userRepository.save(user);
//
//        return session;
//    }
//
//    public boolean isSessionValid(String tokenValue) {
//        User user = userRepository.findBySessionToken(tokenValue);
//        if (user == null) {
//            return false; // Session not found
//        }
//        UserSession session = user.getSessionByToken(tokenValue);
//        if (session == null) {
//            return false; // Session not found for this user
//        }
//        LocalDateTime now = LocalDateTime.now();
//        if (now.isAfter(session.getExpirationTime())) {
//            session.setTokenStatus("EXPIRED");
//            userRepository.save(user); // Update the user's session status
//            return false; // Session has expired
//        }
//        return "ACTIVE".equals(session.getTokenStatus());
//    }
//
//    public void cleanUpExpiredSessions() {
//        LocalDateTime now = LocalDateTime.now();
//        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//            for (UserSession session : user.getUserSessions()) {
//                if (now.isAfter(session.getExpirationTime())) {
//                    session.setTokenStatus("EXPIRED");
//                }
//            }
//            userRepository.save(user);
//        }
//    }
//
//}
