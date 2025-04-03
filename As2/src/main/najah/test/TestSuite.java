package main.najah.test;

import org.junit.platform.suite.api.*;

//@SelectPackages(value = { "main.najah.test" }) هذه طريقة اولى وفي كمان الي تحتها وفي كمان وحدة للاحتياط فقط 
@Suite
@SelectClasses({
    CalculatorTest.class,
    ProductTest.class,
    UserServiceTest.class,
    RecipeBookTest.class,
   
})
@SuiteDisplayName("All Tests Suite")
public class TestSuite {
}
