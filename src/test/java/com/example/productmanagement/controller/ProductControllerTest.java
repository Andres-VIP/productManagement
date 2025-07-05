package com.example.productmanagement.controller;

import com.example.productmanagement.entity.Product;
import com.example.productmanagement.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController();

        // Inyección manual del mock porque no usamos Spring
        try {
            var field = ProductController.class.getDeclaredField("productService");
            field.setAccessible(true);
            field.set(productController, productService);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo inyectar el mock del servicio", e);
        }
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Producto 1", 10.0),
                new Product(2L, "Producto 2", 20.0)
        );

        when(productService.findAll()).thenReturn(products);

        List<Product> result = productController.getAllProducts();

        assertEquals(2, result.size());
        verify(productService, times(1)).findAll();
    }

    @Test
    void testGetProductByIdFound() {
        Product product = new Product(1L, "Producto", 15.0);
        when(productService.findById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Producto", response.getBody().getName());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productService.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(99L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateProduct() {
        Product product = new Product(null, "Nuevo", 30.0);
        Product saved = new Product(1L, "Nuevo", 30.0);

        when(productService.save(product)).thenReturn(saved);

        Product result = productController.createProduct(product);

        assertNotNull(result);
        assertEquals("Nuevo", result.getName());
        assertEquals(30.0, result.getPrice());
    }

    @Test
    void testUpdateProductFound() {
        Product existing = new Product(1L, "Viejo", 10.0);
        Product updated = new Product(1L, "Actualizado", 99.0);

        when(productService.findById(1L)).thenReturn(Optional.of(existing));
        when(productService.save(any(Product.class))).thenReturn(updated);

        ResponseEntity<Product> response = productController.updateProduct(1L, updated);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Actualizado", response.getBody().getName());
        assertEquals(99.0, response.getBody().getPrice());
    }

    @Test
    void testUpdateProductNotFound() {
        when(productService.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateProduct(999L, new Product());

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteProductFound() {
        when(productService.findById(1L)).thenReturn(Optional.of(new Product()));
        doNothing().when(productService).deleteById(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(productService).deleteById(1L);
    }

    @Test
    void testDeleteProductNotFound() {
        when(productService.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = productController.deleteProduct(99L);

        assertEquals(404, response.getStatusCodeValue());
    }
}
