package com.example.app_gestion_estudiantil.service;

import com.example.app_gestion_estudiantil.user.UserRepository;
import com.example.app_gestion_estudiantil.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User u) {
        return userRepository.save(u);
    }

    public Optional<User> getUser2(Long id) {
        return userRepository.getUser(id);
    }

}

