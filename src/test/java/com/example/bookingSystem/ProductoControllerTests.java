package com.example.bookingSystem;

import com.alexandergonzalez.miTienditaSystem.controller.ProductController;
import com.alexandergonzalez.miTienditaSystem.dto.ProductDto;
import com.alexandergonzalez.miTienditaSystem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void createProduct_success() {
        // Datos de prueba
        ProductDto productDto = new ProductDto("Test Product", 100.0, "Product details");

        // Comportamiento del mock
        when(productService.saveProduct(productDto)).thenReturn(productDto);

        // Llamar al método del controlador
        ResponseEntity<Object> response = productController.createProduct(productDto);

        // Aserciones
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        Map<String, ProductDto> responseBody = (Map<String, ProductDto>) response.getBody();
        ProductDto createdProduct = responseBody.get("Producto creado:");

        // Verificar campo por campo
        assertNotNull(createdProduct); // Asegura que el producto creado no sea nulo
        assertEquals("Test Product", createdProduct.getName());
        assertEquals(100.0, createdProduct.getPrice());
        assertEquals("Product details", createdProduct.getDetails());
    }


    @Test
    void FindById_found(){
        ProductDto productDto = new ProductDto("Test Product", 100.0, "Product details");
        String id_to_found = "123";
        when(productService.getProductByID(id_to_found)).thenReturn(productDto);
        ResponseEntity<Object> response = productController.findById(id_to_found);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, ProductDto> responseBody = (Map<String, ProductDto>) response.getBody();
        ProductDto productoResponse = responseBody.get("Producto encontrado:");
        assertEquals("Test Product", productoResponse.getName());
    }

    @Test
    void update_succes(){
        ProductDto productoDto_update = new ProductDto("Test updated", 10.10, "updated details");
        String id = "132";

        when(productService.getProductByID(id)).thenReturn(productoDto_update);
        when(productService.updateProducto(id, productoDto_update)).thenReturn(productoDto_update);

        ResponseEntity<Object> response = productController.updateProduct(id, productoDto_update);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, ProductDto> responseBody = (Map<String, ProductDto>) response.getBody();
        ProductDto productoResponse = responseBody.get("Producto actualizado:");
        assertEquals("Test updated", productoResponse.getName());
    }

    @Test
    void delete_succes(){
        ProductDto productDto_deleted = new ProductDto("Test deleted", 10.10, "updated details");
        String id = "123";
        when(productService.getProductByID(id)).thenReturn(productDto_deleted);
        when(productService.deleteProduct(id)).thenReturn(productDto_deleted);

        ResponseEntity<Object> response = productController.deleteProduct(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        String productoResponse = (String) responseBody.get("Producto eliminado:");

        assertEquals("Test deleted", productoResponse);
    }

    @Test
    void delete_error(){
        String id = "123";
        when(productService.getProductByID(id)).thenReturn(null);

        ResponseEntity<Object> response = productController.deleteProduct(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        String productoResponse = (String) responseBody.get("message:");
        assertEquals("El producto aún no existe", productoResponse);
    }




}
