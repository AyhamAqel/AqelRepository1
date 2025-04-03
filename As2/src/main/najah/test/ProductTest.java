package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.TimeUnit;

import main.najah.code.Product;

@DisplayName("Product Tests")
@Execution (value = ExecutionMode.CONCURRENT)// مطلوب نعملها مرة هيها ******** 
public class ProductTest {
    Product product;

    @BeforeAll                                // طبقت الافتر والبيفور افي اكثر من تست كيس ا
    static void beforeAll() {
        System.out.println("Starting Product Tests...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Product Tests Completed.");
    }

    @BeforeEach
    void setUp() {
        product = new Product("Laptop", 1000.0);
        System.out.println("Setting up a new Product instance.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed.");
    }

    @Test
    @DisplayName("Test Product Creation with Valid Price")
    void testProductCreationValidPrice() {
        assertEquals("Laptop", product.getName());
        assertEquals(1000.0, product.getPrice());
    }

    @Test
    @DisplayName("Test Product Creation with Negative Price")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testProductCreationNegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Product("Phone", -500));
        assertEquals("Price must be non-negative", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test Apply Discount with Valid Values")
    @CsvSource({ "10,900", "20,800", "50,500" })
    void testApplyValidDiscount(double discount, double expectedPrice) {
        product.applyDiscount(discount);
        assertEquals(expectedPrice, product.getFinalPrice());
    }

    @Test
    @DisplayName("Test Apply Discount with Invalid Values")
    void testApplyInvalidDiscount() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-10));
        assertEquals("Invalid discount", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60));
        assertEquals("Invalid discount", exception2.getMessage());
    }

    @Test
    @DisplayName("Test Get Final Price Without Discount")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testGetFinalPriceWithoutDiscount() {
        assertEquals(1000.0, product.getFinalPrice());
    }

    @Test
    @DisplayName("Timeout Test for Get Final Price")   
    void testGetFinalPriceTimeout() {
        assertEquals(1000.0, product.getFinalPrice());
    }

    @Test
    @Disabled("Bug:")
    @DisplayName("Intentionally Failing Test")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void failingTest() {                       // بس اشغلها برتفع الكافرج للنسبة المطلوبة
        product.applyDiscount(30);
        assertEquals(500, product.getFinalPrice()); // يجب أن يكون 700 وليس 500
    }
}
