package com.example.spring_security_with_mongodb.controller;

import java.util.List;

import com.example.spring_security_with_mongodb.models.AuthenticationRequest;
import com.example.spring_security_with_mongodb.models.User;
import com.example.spring_security_with_mongodb.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/subs")
    public ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        User user = new User();
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        List<User> list = userService.findAllUsers();

        try {
            for (User u : list) {
                if (u.equals(user)) {
                    System.out.println(user);
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("USERNAME ALREADY REGISTERED");
        }

        try {

            userService.setUsers(user);
            return ResponseEntity.ok().body("USER HAS BEEN ADDED SUCCESSFULLY!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("SOME INTERNAL ERROR HAS HAPPENED, CANNOT ADD USER");
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return ResponseEntity.ok().body("SUCCESSFULLY LOGGED IN");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USERNAME OR PASSWORD IS INCORRECT");
        }
    }
}
