package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Timeout;

import main.najah.code.Calculator;
import java.util.concurrent.TimeUnit;

@DisplayName("Calculator Tests")
@TestMethodOrder(OrderAnnotation.class)                   
public class CalculatorTest {

    Calculator calc;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting Calculator Tests..."); 
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Calculator Tests Completed");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("Setting up a new Calculator instance");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed");
    }

    @Test
    @Order(1)
    @DisplayName("Test Addition with Positive Numbers")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testAddPositiveNumbers() {
        assertEquals(10, calc.add(4, 3, 3));
    }

    @Test
    @Order(2)
    @DisplayName("Test Addition with Negative Numbers")
    void testAddNegativeNumbers() {
        assertEquals(-6, calc.add(-2, -2, -2));
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("Test Addition with Parameterized Values")
    @CsvSource({ "1,2,3,6", "2,2,2,6", "-1,-1,-1,-3" }) // بقدر اعملهن بملف بس نوعت بطرق التست عشان اشمل كلشي اخدناه
    void testAddParameterized(int a, int b, int c, int expected) {
        assertEquals(expected, calc.add(a, b, c));
    }

    @Test
    @Order(4)
    @DisplayName("Test Division by Zero")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("Test Valid Division")
    void testValidDivision() {
        assertEquals(5, calc.divide(10, 2));
    }

    @Test
    @Order(6)
    @DisplayName("Test Factorial of Positive Number")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testFactorialPositive() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    @Order(7)
    @DisplayName("Test Factorial of Zero")
    void testFactorialZero() {
        assertEquals(1, calc.factorial(0));
    }

    @Test
    @Order(8)
    @DisplayName("Test Factorial of Negative Number")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testFactorialNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.factorial(-5));
        assertEquals("Negative input", exception.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("Timeout Test for Factorial")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testFactorialTimeout() {
        assertEquals(3628800, calc.factorial(10));
    }

    @Test
    @Disabled("Bug:")
    @DisplayName("Intentionally Failing Test")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void failingTest() {
        assertEquals(0, calc.factorial(5)); // يجب أن يكون 120 وليس 0
    }
}
