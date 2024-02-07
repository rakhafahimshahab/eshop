package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util. Iterator;
import static org.junit. jupiter.api.Assertions.*;
@ExtendWith (MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testEditProduct() {
        // Setup: Create and add a product
        Product originalProduct = new Product();
        originalProduct.setProductId("eb558e9-1c39-460e-8860-71af6af63bd6");
        originalProduct.setProductName("Sampo Cap Bambang");
        originalProduct.setProductQuantity(100);
        productRepository.create(originalProduct);

        // Action: Edit the product
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9-1c39-460e-8860-71af6af63bd6"); // Same ID as original
        updatedProduct.setProductName("Sampo Cap");
        updatedProduct.setProductQuantity(20);
        productRepository.edit(updatedProduct);

        // Assertion: Check if the product was updated correctly
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product productAfterEdit = productIterator.next();
        assertEquals("Sampo Cap", productAfterEdit.getProductName());
        assertEquals(20, productAfterEdit.getProductQuantity());

        // Ensure no additional products were added
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testDeleteProduct() {
        // Setup: Create and add two products
        Product product1 = new Product();
        product1.setProductId("eb558e9-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("72439bdd-1921-4050-bfa1-2054eb1fe585");
        product2.setProductName("Rapi kerdil");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        // Action: Delete the first product
        productRepository.delete(product1);

        // Assertion: Check if the product was removed and only product2 remains
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product remainingProduct = productIterator.next();
        assertEquals("72439bdd-1921-4050-bfa1-2054eb1fe585", remainingProduct.getProductId());

        // Ensure no more products are present
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditNonExistentProduct() {
        // Setup: Assume no product with this ID exists
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("72439bdd-1921-4050-bfa1-2054eb1fe585");
        nonExistentProduct.setProductName("Rapi kerdil");
        nonExistentProduct.setProductQuantity(20);

        // Action: Attempt to edit a non-existent product
        productRepository.edit(nonExistentProduct);

        // Assertion: Ensure the repository is unchanged (empty in this case)
        assertFalse(productRepository.findAll().hasNext());
    }
    @Test
    void testDeleteNonExistentProduct() {
        // Setup: Create and add a product, ensure another product does not exist
        Product existingProduct = new Product();
        existingProduct.setProductId("eb558e9-1c39-460e-8860-71af6af63bd6");
        productRepository.create(existingProduct);

        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("72439bdd-1921-4050-bfa1-2054eb1fe585");

        // Action: Attempt to delete a non-existent product
        productRepository.delete(nonExistentProduct);

        // Assertion: Ensure the repository still contains the existing product
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product remainingProduct = productIterator.next();
        assertEquals("eb558e9-1c39-460e-8860-71af6af63bd6", remainingProduct.getProductId());
        // Ensure no additional products were mistakenly removed
        assertFalse(productIterator.hasNext());
    }

}