package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;
import java.util.Collections;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Ensure findAll() returns an empty iterator by default for all tests
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        productService.create(product);

        verify(productRepository).create(productCaptor.capture());
        Product capturedProduct = productCaptor.getValue();
        assertNotNull(capturedProduct.getProductId(), "Product ID should not be null after creation");
    }

    @Test
    void testGetProduct() {
        String productId = UUID.randomUUID().toString();
        Product product = new Product();
        product.setProductId(productId);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());

        Product result = productService.get(productId);

        assertEquals(productId, result.getProductId(), "The retrieved product ID should match the requested ID");
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        productService.edit(product);

        verify(productRepository).edit(productCaptor.capture());
        assertEquals(product.getProductId(), productCaptor.getValue().getProductId(), "The edited product should have the same ID");
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        productService.delete(product);

        verify(productRepository).delete(productCaptor.capture());
        assertEquals(product, productCaptor.getValue(), "The deleted product should match the provided product");
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2).iterator());

        List<Product> products = productService.findAll();

        assertEquals(2, products.size(), "findAll should return all products");
    }

}
