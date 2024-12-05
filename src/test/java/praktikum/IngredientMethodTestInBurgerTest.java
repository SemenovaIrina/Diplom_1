package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static praktikum.data.UtilsForDataPrepare.stringRandomGenerate;

public class IngredientMethodTestInBurgerTest {

    private Burger burger;

    @BeforeEach
    public void setUp() {
        Random rnd = new Random();
        burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, stringRandomGenerate(rnd.nextInt(10)), rnd.nextFloat() * 100);
        burger.ingredients.add(ingredient);
        ingredient = new Ingredient(IngredientType.SAUCE, stringRandomGenerate(rnd.nextInt(10)), rnd.nextFloat() * 100);
        burger.ingredients.add(ingredient);
        ingredient = new Ingredient(IngredientType.FILLING, stringRandomGenerate(rnd.nextInt(10)), rnd.nextFloat() * 100);
        burger.ingredients.add(ingredient);
    }

    @Test
    void addIngredientAddIngredientToBurger() {
        Random rnd = new Random();
        IngredientType type = IngredientType.values()[rnd.nextInt(2)];
        String ingredientName = stringRandomGenerate(rnd.nextInt(10));
        float ingredientPrice = rnd.nextFloat() * 100;
        Ingredient ingredient = new Ingredient(type, ingredientName, ingredientPrice);
        int ingredientCount = burger.ingredients.size();
        burger.addIngredient(ingredient);
        //проверяем, что добавление действительно произошло
        int actualIngredientCount = burger.ingredients.size();
        Assertions.assertEquals(ingredientCount + 1, actualIngredientCount, "Количество ингредиентов после добавления не соответствует ожидаемому");
        Assertions.assertTrue(burger.ingredients.contains(ingredient), "Добавленный ингредиент отсутствует в списке");

    }

    @Test
    void removeIngredientDeleteIngredientFromBurger() {
        Random rnd = new Random();
        int ingredientNumber = rnd.nextInt(burger.ingredients.size());
        Ingredient ingredient = burger.ingredients.get(ingredientNumber);
        int ingredientCount = burger.ingredients.size();
        burger.removeIngredient(ingredientNumber);
        //проверяем, что удаление действительно произошло
        int actualIngredientCount = burger.ingredients.size();
        Assertions.assertEquals(ingredientCount - 1, actualIngredientCount, "Количество ингредиентов после удаления не соответствует ожидаемому");
        Assertions.assertFalse(burger.ingredients.contains(ingredient), "Добавленный ингредиент отсутствует в списке");
    }

    @Test
    void moveIngredientChangeIngredientPlaceInBurger() {
        Random rnd = new Random();
        int ingredientNumber = rnd.nextInt(burger.ingredients.size());
        int ingredientNewNumber = rnd.nextInt(burger.ingredients.size());
        Ingredient ingredient = burger.ingredients.get(ingredientNumber);
        burger.moveIngredient(ingredientNumber, ingredientNewNumber);
        //проверяем, что перемещение действительно произошло
        Ingredient ingredientAfterMove = burger.ingredients.get(ingredientNewNumber);
        Assertions.assertEquals(ingredient, ingredientAfterMove, "Место ингредиента не соответствует ожидаемому");
    }
}
