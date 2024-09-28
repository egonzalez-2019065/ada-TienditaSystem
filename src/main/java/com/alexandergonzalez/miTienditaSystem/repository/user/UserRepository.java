package com.alexandergonzalez.miTienditaSystem.repository.user;

import com.alexandergonzalez.miTienditaSystem.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
