package com.coderhack.leaderboard.Entites;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private int score;
    private Set<String> badges = new HashSet<>();
}
