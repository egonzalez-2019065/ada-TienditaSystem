package com.alexandergonzalez.miTienditaSystem.repository.product;

import com.alexandergonzalez.miTienditaSystem.models.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
}
