package com.example.authentication;

import com.example.entity.User;
import com.example.entity.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {
    private final JwtUtil jwtUtil;
    private final LoginService loginService;
//    @Autowired
//    private RestTemplate restTemplate;


    @Autowired
    public LoginController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        User user = loginService.login(email, password);

        if (user != null) {


            // Generate a JWT token
            String token = jwtUtil.generateToken(user);
            user.getUserSessions().add(new UserSession(token));
            // Claiming data:


            String sub = jwtUtil.extractClaimsAsJsonString(token);
            System.out.println("Token Original Subject: "+sub);
            //Validity
            Boolean valid = jwtUtil.isTokenExpired(token);
            System.out.println("Is Token Valid: "+valid);


            return ResponseEntity.ok().body("{\nToken: "+token+"\n}");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed"); // Handle login failure
    }

    //Logout

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
            User user = loginService.login("johndoe@example.com","123");
            jwtUtil.logout(user.getUserSessions().get(0).getAuthToken());
            return ResponseEntity.ok().body("{\nToken: "+"Successfull Logged Out"+"\n}");
        }

    @PostMapping("/tokenValidity")
    public ResponseEntity<String> tokenCheck() {
        User user = loginService.login("johndoe@example.com","123");
//        jwtUtil.logout(user.getUserSessions().get(0).getAuthToken());
        if(!jwtUtil.isTokenExpired(user.getUserSessions().get(0).getAuthToken()))
        {
            return ResponseEntity.ok().body("{\nToken: "+"Your Token Has Ended"+"\n}");
        }
        return ResponseEntity.ok().body("{\nToken: "+"Your Token is Active"+"\n}");
    }

//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed"); // Handle login failure
//    }
}
