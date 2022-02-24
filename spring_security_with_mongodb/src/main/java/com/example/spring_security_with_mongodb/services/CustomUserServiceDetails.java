package com.example.spring_security_with_mongodb.services;

import com.example.spring_security_with_mongodb.models.CustomUserDetails;
import com.example.spring_security_with_mongodb.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceDetails implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userService.findUserByUsername(username);
        System.out.println(user);
        if (user == null)
            throw new UsernameNotFoundException("INVALID USERNAME!!");
        return new CustomUserDetails(user);
    }
}
