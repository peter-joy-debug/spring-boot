package com.example.controller;

import com.example.entity.User;
import com.example.middleware.RouteResolveService;
import com.example.middleware.SecurityConfig;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private SecurityConfig securityConfig;

    private RouteResolveService routeResolveService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User updated = userService.updateUser(id, updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


//    @GetMapping("/{prefix}/{name}")
//    public ResponseEntity<List<User>> getAllUsers(@PathVariable String prefix, @PathVariable String name) {
//        if(prefix.equals("users") && name.equals("all"))
//        {
//            System.out.println("PREFIX: "+prefix+"\n"+
//                    "NAME: "+name);
//        }
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.status(HttpStatus.OK).body(users);
//    }
    @PostMapping("/saveAll")
    public ResponseEntity<List<User>> saveAllUsers(@RequestBody List<User> users) {
        List<User> savedUsers = userService.saveAll(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers);
    }
}


