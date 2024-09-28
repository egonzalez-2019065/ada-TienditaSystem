package com.alexandergonzalez.miTienditaSystem.service.product;

import com.alexandergonzalez.miTienditaSystem.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alexandergonzalez.miTienditaSystem.repository.product.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceMap {

    // Declaración de la constatante del repositorio
    private final ProductRepository productRepository;

    // Constructor con la constante
    @Autowired
    public ProductServiceMap(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Declaración de un método que guardará un producto nuevo en la base de datos
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Declaración de un método que actualizará un solo producto existente en la base de datos
    public Product updateBooking(String id, Product product) {
        if (productRepository.existsById(id)) {
            return productRepository.save(product);
        }
        return null;
    }

    // Declaración de un método que retornará todos los productos existentes en la base de datos
    public List<Product> getAllProductos() {
        return productRepository.findAll();
    }

    // Declaración de un método que retornará un solo producto existente en la base de datos
    public Optional<Product> getProductByID(String id) {
        return productRepository.findById(id);
    }

    // Declaración de un método que eliminará un producto existente en la base de datos
    public void deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
