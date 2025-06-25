package com.smart.config;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // In our case, the username is actually the email
        Optional<User> userOptional = userRepo.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user with email: " + email);
        }
        
        User user = userOptional.get();
        return new CustomUserDetails(user);
    }
}
