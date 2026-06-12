package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(String email, String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Compte déjà existant");
        }
        userRepository.save(new User(email, username, password));
        return "Inscription réussie";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Identifiants incorrects"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Identifiants incorrects");
        }
        return "Redirection vers la page d'accueil";
    }
}