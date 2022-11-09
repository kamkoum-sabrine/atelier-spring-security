package com.example.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.DAO.UserRepository;
import com.example.entities.User;



@Service
@lombok.extern.slf4j.Slf4j
public class UserService implements UserDetailsService {
private final UserRepository userRepository;
@Autowired
public UserService(UserRepository userRepository) {
 this.userRepository = userRepository;
 }
public List<User> getAllUsers() {
    return userRepository.findAll();
}
@Override
public UserDetails loadUserByUsername(String username) throws
UsernameNotFoundException {
 Objects.requireNonNull(username);
 User user = userRepository.findUserWithName(username)
 .orElseThrow(() -> new UsernameNotFoundException("User not found"));
 return user;
 }
} 
