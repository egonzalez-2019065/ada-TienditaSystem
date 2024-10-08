package com.alexandergonzalez.miTienditaSystem.service;

import com.alexandergonzalez.miTienditaSystem.entity.Product;
import com.alexandergonzalez.miTienditaSystem.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alexandergonzalez.miTienditaSystem.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    // Declaración de la constatante del repositorio
    private final ProductRepository productRepository;

    // Constructor con la constante
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductDto toDto(Product product){
        return new ProductDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getDetails()

        );
    }
    // Declaración de un método que guardará un producto nuevo en la base de datos
    public ProductDto saveProduct(ProductDto productDto) {
        Product productToSave = new Product();
        productToSave.setName(productDto.getName());
        productToSave.setDetails(productDto.getDetails());
        productToSave.setPrice(productDto.getPrice());
        productRepository.save(productToSave);
        return this.toDto(productToSave);
    }

    // Declaración de un método que actualizará un solo producto existente en la base de datos
    public ProductDto updateProducto(String id, ProductDto productDto) {
        Product productToSave = productRepository.findById(id).orElse(null);
        if (productToSave != null) {
            productToSave.setName(productDto.getName());
            productToSave.setDetails(productDto.getDetails());
            productToSave.setPrice(productDto.getPrice());
            Product updatedProduct = productRepository.save(productToSave);
            return this.toDto(updatedProduct);
        }
        return null;
    }

    // Declaración de un método que retornará todos los productos existentes en la base de datos
    public List<ProductDto> getAllProductos() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    // Declaración de un método que retornará un solo producto existente en la base de datos
    public ProductDto getProductByID(String id) {
       Product productFound = productRepository.findById(id).orElse(null);
       if(productFound != null){
           return this.toDto(productFound);
       }
       return null;
    }

    // Declaración de un método que eliminará un producto existente en la base de datos
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.deleteById(id);
        }
    }
}
