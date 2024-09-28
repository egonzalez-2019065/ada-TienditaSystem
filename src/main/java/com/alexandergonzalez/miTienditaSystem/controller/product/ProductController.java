package com.alexandergonzalez.miTienditaSystem.controller.product;

import com.alexandergonzalez.miTienditaSystem.models.product.Product;
import com.alexandergonzalez.miTienditaSystem.models.product.ProductDto;
import com.alexandergonzalez.miTienditaSystem.service.product.ProductServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/product/")
public class ProductController {

    private final ProductServiceMap productServiceMap;

    @Autowired
    public ProductController(ProductServiceMap productServiceMap) {
        this.productServiceMap = productServiceMap;
    }


    @PostMapping
    public ResponseEntity<Product> createUser(@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        Product savedProduct = productServiceMap.saveProduct(product);
        URI createdProductURI = URI.create("/v1/product/" + savedProduct.getId());
        return ResponseEntity.created(createdProductURI).build();
    }

    @GetMapping
    public ResponseEntity<List<Product>>getAllBookings() {
        List<Product> products = productServiceMap.getAllProductos();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Optional <Product> productFound = productServiceMap.getProductByID(id);
        if (productFound.isPresent()) {
            return ResponseEntity.ok(productFound.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateBooking(@PathVariable String id, @RequestBody ProductDto productDto) {
        Optional <Product> product = productServiceMap.getProductByID(id);
        if (product.isPresent()) {
            Product userToUpdated = product.get();
            userToUpdated.update(productDto);
            productServiceMap.saveProduct(userToUpdated);
            return ResponseEntity.ok(userToUpdated);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        Optional <Product> product = productServiceMap.getProductByID(id);
        if (product.isPresent()) {
            productServiceMap.deleteProduct(id);
            return ResponseEntity.ok().build();
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
