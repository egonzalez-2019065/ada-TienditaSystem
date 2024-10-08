package com.alexandergonzalez.miTienditaSystem.controller;

import com.alexandergonzalez.miTienditaSystem.dto.ProductDto;
import com.alexandergonzalez.miTienditaSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/product/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ProductDto> createUser(@RequestBody ProductDto productDto) {
        ProductDto productoDtoToSave = productService.saveProduct(productDto);

        return ResponseEntity.ok(productoDtoToSave);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>>getAllBookings() {
        List<ProductDto> products = productService.getAllProductos();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable String id) {
        ProductDto productFound = productService.getProductByID(id);
        if (productFound != null) {
            return ResponseEntity.ok(productFound);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        ProductDto product = productService.getProductByID(id);
        if (product != null) {
            ProductDto updatedProduct = productService.updateProducto(id, productDto);
            return ResponseEntity.ok(updatedProduct);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        ProductDto product = productService.getProductByID(id);
        if (product != null) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
