package com.alexandergonzalez.miTienditaSystem.repository;

import com.alexandergonzalez.miTienditaSystem.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
