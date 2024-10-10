package com.alexandergonzalez.miTienditaSystem.controller;

import com.alexandergonzalez.miTienditaSystem.dto.ProductDto;
import com.alexandergonzalez.miTienditaSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/product/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody ProductDto productDto) {
        Map<String, Object> response = new HashMap<>();
        ProductDto productoDtoToSave = productService.saveProduct(productDto);
        response.put("Producto creado:", productoDtoToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>>getAllBookings() {
        List<ProductDto> products = productService.getAllProductos();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        ProductDto productFound = productService.getProductByID(id);
        if (productFound != null) {
            response.put("Producto encontrado:", productFound);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("message", "El producto aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        Map<String, Object> response = new HashMap<>();

        ProductDto product = productService.getProductByID(id);
        if (product != null) {
            ProductDto updatedProduct = productService.updateProducto(id, productDto);
            response.put("Producto actualizado:", updatedProduct);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("message", "El producto aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        ProductDto product = productService.getProductByID(id);
        if (product != null) {
            ProductDto productDeleted = productService.deleteProduct(id);
            response.put("Producto eliminado:", productDeleted.getName());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("message", "El producto aún no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
