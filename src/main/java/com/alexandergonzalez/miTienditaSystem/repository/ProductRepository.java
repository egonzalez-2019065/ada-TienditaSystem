package com.alexandergonzalez.miTienditaSystem.repository;

import com.alexandergonzalez.miTienditaSystem.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
}
