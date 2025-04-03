package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import main.najah.code.*;

@DisplayName("RecipeBook Tests")
public class RecipeBookTest {

    RecipeBook recipeBook;
    Recipe recipe1, recipe2, recipe3, recipe4, recipe5;

    @SuppressWarnings("unused")
	private Recipe createRecipe(String name, String price, String coffee, String milk, String sugar, String chocolate) throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setPrice(price);
        recipe.setAmtCoffee(coffee);
        recipe.setAmtMilk(milk);
        recipe.setAmtSugar(sugar);
        recipe.setAmtChocolate(chocolate);
        return recipe;
    }

    @BeforeEach
    void setUp() throws RecipeException {
        recipeBook = new RecipeBook();
        recipe1 = createRecipe("Espresso", "5", "2", "0", "1", "0");
        recipe2 = createRecipe("Latte", "10", "1", "2", "1", "0");
        recipe3 = createRecipe("Mocha", "15", "1", "1", "1", "2");
        recipe4 = createRecipe("Cappuccino", "12", "1", "1", "2", "0");
        recipe5 = createRecipe("Americano", "8", "2", "0", "0", "0");
    }

    @Test
    @DisplayName("Test Adding Recipes")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testAddRecipe() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertTrue(recipeBook.addRecipe(recipe2));
        assertTrue(recipeBook.addRecipe(recipe3));
        assertTrue(recipeBook.addRecipe(recipe4));
        assertFalse(recipeBook.addRecipe(recipe5)); // يجب ان تفشل لان الكتاب ممتلئ
    }

    @Test
    @DisplayName("Test Preventing Duplicate Recipes")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testPreventDuplicateRecipe() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertFalse(recipeBook.addRecipe(recipe1));
    }

    @Test
    @Disabled
    @DisplayName("Test Deleting Recipe")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testDeleteRecipe() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Espresso", recipeBook.deleteRecipe(0));
        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    @DisplayName("Test Editing Recipe")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testEditRecipe() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Espresso", recipeBook.editRecipe(0, recipe2));
        assertEquals("", recipeBook.getRecipes()[0].getName()); 
    }

    @Test
    @DisplayName("Test Retrieving Recipes")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testGetRecipes() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        Recipe[] recipes = recipeBook.getRecipes();
        assertTrue(Arrays.asList(recipes).contains(recipe1));
        assertTrue(Arrays.asList(recipes).contains(recipe2));
    }

    @ParameterizedTest
    @CsvSource({
        "Espresso,5,2,0,1,0",
        "Latte,10,1,2,1,0",
        "Mocha,15,1,1,1,2",
        "Cappuccino,12,1,1,2,0",
        "Americano,8,2,0,0,0"
    })
    @DisplayName("Test Adding Recipes with Parameters")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testAddRecipeParameterized(String name, String price, String coffee, String milk, String sugar, String chocolate) throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setPrice(price);
        recipe.setAmtCoffee(coffee);
        recipe.setAmtMilk(milk);
        recipe.setAmtSugar(sugar);
        recipe.setAmtChocolate(chocolate);
        assertTrue(recipeBook.addRecipe(recipe));
    }

    @Test
    @Disabled("This test is intentionally failing due to incorrect assumption.")// يجب اضافة وصفة قبل حذفها
    @DisplayName("Intentionally Failing Test")
    void testFailing() {
        assertNull(recipeBook.deleteRecipe(0));
    }
}
