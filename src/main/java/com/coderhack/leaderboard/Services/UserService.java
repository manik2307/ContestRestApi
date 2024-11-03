package com.coderhack.leaderboard.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhack.leaderboard.Repository.UserRepository;


import com.coderhack.leaderboard.Entites.User;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User registerUser(User user) {
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int newScore) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setScore(newScore);
        updateBadges(user);
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private void updateBadges(User user) {
        user.getBadges().clear();
        int score = user.getScore();
        if (score >= 1 && score < 30) {
            user.getBadges().add("Code Ninja");
        }
        if (score >= 30 && score < 60) {
            user.getBadges().add("Code Champ");
        }
        if (score >= 60 && score <= 100) {
            user.getBadges().add("Code Master");
        }
    }
}