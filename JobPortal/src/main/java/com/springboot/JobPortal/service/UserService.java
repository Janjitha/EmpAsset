package com.springboot.JobPortal.service;

import com.springboot.JobPortal.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.springboot.JobPortal.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}