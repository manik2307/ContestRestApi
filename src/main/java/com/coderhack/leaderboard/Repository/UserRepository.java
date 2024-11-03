package com.coderhack.leaderboard.Repository;
import com.coderhack.leaderboard.Entites.User;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepository extends MongoRepository<User,String>{
    
}
 