import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        // Clear the database before each test to ensure isolation
        productService.deleteAllProducts();
    }

    @AfterEach
    void tearDown() {
        // Clear the database after each test to clean up
        productService.deleteAllProducts();
    }

    @Test
    void testAddProduct() {
        Product product = new Product(1, "TestProduct", 10.0, 100, "TestOrigin");
        productService.addProduct(product);

        // Print to verify product addition
        Product addedProduct = productService.getProductById(1);
        System.out.println("Added product: " + addedProduct);

        // Assert the product exists
        assertNotNull(addedProduct);
        assertTrue(productService.productExists(1));

    }
    @Test
    void testRemoveProduct() {
        Product product = new Product(1, "TestProduct", 10.0, 100, "TestOrigin");
        productService.addProduct(product);

        // Print to verify product addition
        System.out.println("Added product: " + productService.getProductById(1));

        productService.removeProduct(1);

        // Check if the product exists after removal
        boolean productExists = productService.productExists(1);

        // Print to verify product removal
        System.out.println("Product exists after removal: " + productExists);

        // Assert that the product no longer exists
        assertFalse(productExists);
    }


    @Test
    void testUpdateProductPrice() {
        Product product = new Product(4500000, "TestProduct", 10.0, 100, "TestOrigin");
        productService.addProduct(product);

        // Print to verify product addition
        System.out.println("Added product: " + productService.getProductById(4500000));

        productService.updateProductPrice(4500000, 15.0);

        // Retrieve updated product
        Product updatedProduct = productService.getProductById(4500000);

        // Print to verify product update
        System.out.println("Updated product: " + updatedProduct);

        // Assert the product is not null and price is updated
        assertNotNull(updatedProduct);
        assertEquals(15.0, updatedProduct.getPrice());
    }


    @Test
    void testUpdateProductQuantity() {
        Product product = new Product(1, "TestProduct", 10.0, 100, "TestOrigin");
        productService.addProduct(product);

        // Print to verify product addition
        Product addedProduct = productService.getProductById(1);
        System.out.println("Added product: " + addedProduct);

        productService.updateProductQuantity(1, 200);

        // Retrieve updated product
        Product updatedProduct = productService.getProductById(1);

        // Print to verify product update
        System.out.println("Updated product: " + updatedProduct);

        // Assert the product is not null and quantity is updated
        assertNotNull(updatedProduct);
        assertEquals(200, updatedProduct.getQuantity());

        // Optionally, display all products
        productService.displayProducts();
    }

    @Test
    void testDeleteAllProducts() {
        Product product1 = new Product(1, "TestProduct1", 10.0, 100, "TestOrigin1");
        Product product2 = new Product(2, "TestProduct2", 20.0, 200, "TestOrigin2");

        productService.addProduct(product1);
        productService.addProduct(product2);

        // Print to verify product addition
        System.out.println("Added products:");
        productService.displayProducts();

        productService.deleteAllProducts();

        // Check if the products exist after deletion
        boolean product1Exists = productService.productExists(1);
        boolean product2Exists = productService.productExists(2);

        // Print to verify product deletion
        System.out.println("Product 1 exists after deletion: " + product1Exists);
        System.out.println("Product 2 exists after deletion: " + product2Exists);

        // Assert that the products no longer exist
        assertFalse(product1Exists);
        assertFalse(product2Exists);
    }

    @Test
    void testDisplayProducts() {
        Product product = new Product(1, "TestProduct", 10.0, 100, "TestOrigin");
        productService.addProduct(product);

        // Capture the output of displayProducts method
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        productService.displayProducts();

        // Reset System.out
        System.setOut(System.out);

        // Check the captured output
        String output = outputStream.toString();
        System.out.println("Captured output: " + output);

        // Assertions to verify the product display
        assertTrue(output.contains("id=1"));
        assertTrue(output.contains("name='TestProduct'"));
        assertTrue(output.contains("price=10.0"));
        assertTrue(output.contains("quantity=100"));
        assertTrue(output.contains("origin='TestOrigin'"));
    }

    //========================================Notes:===================================================================
    //Product Verification: In testUpdateProductPrice and testUpdateProductQuantity, ideally,
    // you would have a method to retrieve a product by its ID to verify the updates.
    // This is not implemented in the provided code, but you can add it to ProductService for a thorough test.

    //Manual Console Check: The testDisplayProducts test case relies on a manual check of the console output.
    // For automated testing, consider implementing a method that returns a list of all products and verify the list
    // content in the test case.
}