package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import main.najah.code.UserService;

@DisplayName("UserService Tests")
@TestMethodOrder(OrderAnnotation.class)                  //  استخدمت الاوردر بملفين اسهل لترتيب التيستات وافضل 
public class UserServiceTest {

    UserService userService;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting UserService Tests...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("UserService Tests Completed");
    }

    @BeforeEach
    void setUp() {
        userService = new UserService();
        System.out.println("Setting up a new UserService instance");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Completed");
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("Test Valid Emails")
    @CsvFileSource(resources = "Test_valid_emails.csv", numLinesToSkip = 1)
    void testValidEmails(String email) {
        assertTrue(userService.isValidEmail(email));
    }

    @ParameterizedTest
    @Order(2)
    @DisplayName("Test Invalid Emails")
    @CsvFileSource(resources = "Test_invalid_emails.csv", numLinesToSkip = 1) // جيت البيانات من الملف موجود 3 ملفات لهذا التست
    void testInvalidEmails(String email) {
        assertFalse(userService.isValidEmail(email));
    }

    @Test
    @Order(3)
    @DisplayName("Test Email Null Case")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testNullEmail() {
        assertFalse(userService.isValidEmail(null));
    }

    @Test
    @Order(4)
    @DisplayName("Test Valid Authentication")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testValidAuthentication() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @ParameterizedTest
    @Order(5)
    @DisplayName("Test Invalid Authentication")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @CsvFileSource(resources = "Test_invalid_auth.csv", numLinesToSkip = 1)
    void testInvalidAuthentication(String username, String password) {
        assertFalse(userService.authenticate(username, password));
    }

    @Test
    @Order(6)
    @DisplayName("Timeout Test for Authentication")
    void testAuthenticationTimeout() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @Order(7)
    @Disabled("This test is intentionally failing. ")
    @DisplayName("Intentionally Failing Authentication Test")
    void testFailingAuthentication() {
        assertTrue(userService.authenticate("wrongUser", "wrongPass")); // عند استخدام البيانات الصحيحة يعمل-admin/1234
    }
}
